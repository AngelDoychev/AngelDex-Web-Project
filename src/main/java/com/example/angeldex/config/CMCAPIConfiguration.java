package com.example.angeldex.config;
import com.example.angeldex.model.entities.Currency;
import com.example.angeldex.repository.CurrencyRepository;
import com.example.angeldex.repository.UserEntityRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CMCAPIConfiguration {
    private final static String API_KEY = "7c21f68e-9e2f-4e45-8f92-b33599e83a0b";
    private final static String API_URI = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CurrencyRepository currencyRepository;
    private final UserEntityRepository userEntityRepository;

    public CMCAPIConfiguration(ModelMapper modelMapper, Gson gson, CurrencyRepository currencyRepository, UserEntityRepository userEntityRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.currencyRepository = currencyRepository;
        this.userEntityRepository = userEntityRepository;
    }
    public void addCurrencies() {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("start","1"));
        parameters.add(new BasicNameValuePair("limit","100"));
        parameters.add(new BasicNameValuePair("convert","USD"));

        try {
            String result = makeAPICall(API_URI, parameters);
            System.out.println(result);
            JsonObject data = this.gson.fromJson(result, JsonObject.class);
            JsonObject[] currencies = this.gson.fromJson(data.get("data").getAsJsonArray(), JsonObject[].class);
            Arrays.stream(currencies).map(e -> {
                        Currency currency = new Currency();
                        currency.setName(e.get("name").toString());
                        JsonObject quote = this.gson.fromJson(e.get("quote"), JsonObject.class);
                        JsonObject usd = this.gson.fromJson(quote.get("USD"), JsonObject.class);
                        currency.setPrice(new BigDecimal(usd.get("price").toString()));
                        currency.setUser(this.userEntityRepository.findUserEntitiesByEmail("user@email.com").get());
                        this.modelMapper.map(e, Currency.class);
                        return currency;
                    })
                    .forEach(currencyRepository::save);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error: cannot access content - " + e.toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Error: Invalid URL " + e.toString());
        }
    }
    public void hardUpdateCurrencies(){
        this.currencyRepository.deleteAll();
        addCurrencies();
    }
    public void updateCurrencies() {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("start","1"));
        parameters.add(new BasicNameValuePair("limit","100"));
        parameters.add(new BasicNameValuePair("convert","USD"));
        try {
            String result = makeAPICall(API_URI, parameters);
            System.out.println(result);
            JsonObject data = this.gson.fromJson(result, JsonObject.class);
            JsonObject[] currencies = this.gson.fromJson(data.get("data").getAsJsonArray(), JsonObject[].class);
            Arrays.stream(currencies).forEach(e -> {
                        JsonObject quote = this.gson.fromJson(e.get("quote"), JsonObject.class);
                        JsonObject usd = this.gson.fromJson(quote.get("USD"), JsonObject.class);
                        this.modelMapper.map(e, Currency.class);
                        Optional<Currency> currencyOpt = this.currencyRepository.findCurrencyByName(e.get("name").toString());
                        if (currencyOpt.isPresent()) {
                            currencyOpt.get().setPrice(new BigDecimal(usd.get("price").toString()));
                            this.currencyRepository.save(currencyOpt.get());
                        }
                    });

        } catch (IOException e) {
            throw new IllegalArgumentException("Error: cannot access content - " + e.toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Error: Invalid URL " + e.toString());
        }
    }

    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", API_KEY);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }
}
