package com.user;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import lab1_5.api.src.main.java.com.api.IpmaCityForecast;
import lab1_5.api.src.main.java.com.api.IpmaService;

import java.util.logging.Logger;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class App {

    // private static final int CITY_ID_AVEIRO = 1010500;
    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void  main(String[] args ) {

        int CITY_ID;

        if (args.length == 0){ 
            CITY_ID = 1010500; // caso o utilizador não coloque nenhum argumento, por defeito, será utilizado o codigo da cidade de Aveiro

        } else {
            CITY_ID = Integer.parseInt( args[0] );
        }
        
        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            // why this isn't working?

            if (forecast != null) {
                logger.info( "max temp for today: " + forecast.getData().
                        listIterator().next().getTMax());
                logger.info( "min temp for today: " + forecast.getData().
                        listIterator().next().getTMin());

                logger.info( "max temp for tomorrow: " + forecast.getData().
                        listIterator().next().next().getTMax());
                logger.info( "min temp for tomorrow: " + forecast.getData().
                        listIterator().next().next().getTMin());
            } else {
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}