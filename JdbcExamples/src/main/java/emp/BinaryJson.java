package emp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import oracle.sql.json.OracleJsonFactory;
import oracle.sql.json.OracleJsonGenerator;
import oracle.sql.json.OracleJsonObject;
import oracle.sql.json.OracleJsonParser;

/**
 * Encodes JSON text as Oracle binary JSON, stores it in a file, and then reads
 * it back again.
 */
public class BinaryJson {

    public static void main(String[] args) throws IOException {
        
        OracleJsonFactory factory = new OracleJsonFactory();
        
        String json = "{\"name\":\"Smith\",  \"job\": \"Programmer\", \"salary\": 50000}";
        OracleJsonParser parser = factory.createJsonTextParser(new StringReader(json));
        
        FileOutputStream out = new FileOutputStream("smith.oson");
        OracleJsonGenerator gen = factory.createJsonBinaryGenerator(out);
        gen.writeParser(parser);
        gen.close();
        
        System.out.println("Wrote binary JSON file smith.oson");
        
        FileInputStream in = new FileInputStream("smith.oson");
        OracleJsonObject obj = factory.createJsonBinaryValue(in).asJsonObject();
        in.close();
        System.out.println("Read binary JSON file smith.oson");
        System.out.println(obj);

    }

}
