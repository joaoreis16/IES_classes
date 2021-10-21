package com.MyWeatherRadar.app;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
import weather.ipma_client.IpmaCityForecast;
import weather.ipma_client.IpmaService;
*/

// import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class App {

    // private static final int CITY_ID_AVEIRO = 1010500;
    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    // private static final Logger logger = Logger.getLogger(App.class.getName());
    private static Logger logger = LogManager.getLogger(App.class.getName());

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

            if (forecast != null) {
                logger.info( "max temp for today: " + forecast.getData().listIterator().next().getTMax());
                logger.info( "min temp for today: " + forecast.getData().listIterator().next().getTMin());
                logger.info( "precipitation prob for today: " + forecast.getData().listIterator().next().getPrecipitaProb());
                logger.info( "latitude: " + forecast.getData().listIterator().next().getLatitude());
                logger.info( "longitude: " + forecast.getData().listIterator().next().getLongitude());

            } else {
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}