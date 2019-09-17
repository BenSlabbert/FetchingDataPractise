
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {

    public static void main(String[] args)  {


        try {

            String url = "https://api.coindesk.com/v1/bpi/currentprice/GBP";
            URL obj = new URL(url);

            // HttpURLConnection instance is making a request
            //openConnection() method of URL class opens the connection to specified URL
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // saving the response code from the request
            int responseCode = con.getResponseCode();

            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("---------------------------------------------");
            System.out.println("Response Code from the HTTP request : " + responseCode);
            System.out.println("---------------------------------------------");

            //creating reader instance to reade the response from the HTTP GET request
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String userInputLine;
            StringBuilder response = new StringBuilder();

            //if there is still something to read from -> then read and append to String builder -> can be mutable
            while ((userInputLine = reader.readLine()) != null) {
                response.append(userInputLine);
            }

            //closing reader
            reader.close();

            //Converting response to JSON object
            JSONObject myresponse = new JSONObject(response.toString());

            // since response got a objects within objects, we need to dig dipper in order to access field values
            JSONObject timeObject = new JSONObject(myresponse.getJSONObject("time").toString());



            JSONObject bpiObject = new JSONObject(myresponse.getJSONObject("bpi").toString());
            JSONObject currencyObjects = new JSONObject(bpiObject.getJSONObject("GBP").toString());


            System.out.println("Data fetched at UTC time/date : " + timeObject.getString("updated"));
            System.out.println("Description : " + currencyObjects.getString("description")  + "\nRate float : " + currencyObjects.getString("rate_float"));


        } catch (Exception e) {
            System.out.println(e);

        }

    }
}











