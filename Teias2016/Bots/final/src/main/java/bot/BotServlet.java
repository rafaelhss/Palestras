package bot;

import org.apache.commons.lang3.StringUtils;
import com.google.appengine.repackaged.com.google.protobuf.TextFormat.ParseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bot.model.Update;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Form Handling Servlet Most of the action for this sample is in
 * webapp/guestbook.jsp, which displays the {@link Greeting}'s. This servlet has
 * one method
 * {@link #doPost(<#HttpServletRequest req#>, <#HttpServletResponse resp#>)}
 * which takes the form data and saves it.
 */
public class BotServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getOutputStream().print("samba");
    }
    
// Process the http POST of the form
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Update.class, new bot.util.TelegramUpdateDeserializer())
                    .create();

            Update c = gson.fromJson(jb.toString(), Update.class);

            processMessage(c);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendResponse(Integer id, String text_response) throws IOException {

        String urlSendTemplate = "https://api.telegram.org/bot236598358:AAHS2ZVtnHo_IGSOd5MqLkXhSI-A1vScaUQ/sendmessage?chat_id=@@CHATID@@&text=@@TEXT@@";
        
        text_response = URLEncoder.encode(text_response, "UTF-8");
        URL url = new URL(urlSendTemplate.replace("@@CHATID@@", id.toString()).replace("@@TEXT@@", text_response));

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }

    private void processMessage(Update c) throws IOException {

        String texto = c.getMessage().getText();

        String defaultResp = "Oi! Eu sou a tillinha, a assistente virtual da Tilla Viana. Nao entendi o que você falou, flor. Sou um robô. Tente ser mais clara...";
        
        String[][] ola = {{"start", "oi", "boa noite", "bom dia", "boa tarde", "tudo bem?", "oie"}, {"Oi! Eu sou a tillinha, a assistente virtual da Tilla Viana. Vou tentar te ajudar com suas comprinhas."}};
        String[][] querCilios = {{"cilios", "caixa", "postiços", "vende"}, {"Você quer comprar cilios? Legal! Me diga a o modelo!"}};
        String[][] falouModelo = {{"modelo", "047", "032", "016", "059"}, {"Claro que temos esse modelo. o Preço é R$25. Onde devemos entregar?"}};
        String[][] localidadeRafa = {{"taguatinga", "aguas claras"}, {"Para tagatinga e aguas claras, pode buscar no uniceub, flor."}};
        String[][] localidadeBert = {{"asa sul", "guará", "asa norte", "lago sul", "lago norte", "sudoeste"}, {"Para essa localidade entregamos sem taxa! mas só acima de 4 caixas ok?."}};
        String[][] localidadeLonge = {{"ceilandia", "gama", "sobradinho", "samambaia", "paranoá"}, {"Infelizmente nao da para entregar aí. Podemos mandar um motoboy. a taxa é 15 reais."}};

        ArrayList<String[][]> listResp = new ArrayList<>();

        listResp.add(ola);
        listResp.add(querCilios);
        listResp.add(falouModelo);
        listResp.add(localidadeRafa);
        listResp.add(localidadeBert);
        listResp.add(localidadeLonge);

        String resposta = defaultResp;

        String[] tokens = texto.split(" ");

        int currMatches = 0;

        for (String[][] keywords : listResp) {
            int matches = processaKeywords(tokens, keywords);
            System.out.println("matches: " + matches + "  currmatches:" + currMatches);
            if (matches > currMatches) {
                resposta = keywords[1][0];
                currMatches = matches;
            }
        }

        sendResponse(c.getMessage().getFrom().getId(), resposta);
    }

    private int processaKeywords(String[] tokens, String[][] keywords) {
        int matches = 0;
        for (String token : tokens) {
            for (String string : keywords[0]) {
                int dist = StringUtils.getLevenshteinDistance(token.toLowerCase().trim(), string.toLowerCase().trim());
                //System.out.println("Lev dist: " + token + " " + string + ":" + dist);
                if (dist <= 1) {
                    matches++;
                }
            }
        }
        return matches;
    }
}
//[END all]
