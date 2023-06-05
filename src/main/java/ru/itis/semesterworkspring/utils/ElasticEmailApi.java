package ru.itis.semesterworkspring.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@PropertySource("classpath:file.properties")
public class ElasticEmailApi {
    @Autowired
    private Environment environment;

    public void sendEmail(String to, String subject){
        try {
            URL obj = new URL(environment.getProperty("SEND_EMAIL_URL", String.class) + "?apikey=" + environment.getProperty("API_KEY", String.class));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Accept", "application/json");

            String postData = "&to=" + to + "&from=" + environment.getProperty("HOST_EMAIL", String.class) + "&subject=" + "code:" + subject;

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        }
        catch (Exception e) {
            throw new RuntimeException("Error in Email API");
      }
    }
}
