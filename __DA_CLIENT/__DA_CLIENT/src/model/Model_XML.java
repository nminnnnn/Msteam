package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import model.community.Model_Prog;

public class Model_XML {
	
    public static void exportToXML(List<Model_Prog> modelList) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn vị trí lưu file XML");
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".xml")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xml");
            }

            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("model_progs");
                doc.appendChild(rootElement);

                for (Model_Prog model : modelList) {
                    Element modelElement = doc.createElement("model_prog");

                    Element progId = doc.createElement("progId");
                    progId.appendChild(doc.createTextNode(String.valueOf(model.getProgId())));
                    modelElement.appendChild(progId);

                    Element projectId = doc.createElement("projectId");
                    projectId.appendChild(doc.createTextNode(String.valueOf(model.getProjectId())));
                    modelElement.appendChild(projectId);

                    Element time = doc.createElement("time");
                    time.appendChild(doc.createTextNode(model.getTime()));
                    modelElement.appendChild(time);

                    Element content = doc.createElement("content");
                    content.appendChild(doc.createTextNode(model.getContent()));
                    modelElement.appendChild(content);

                    rootElement.appendChild(modelElement);
                }

                // Chuyển tài liệu thành chuỗi XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new StringWriter());
                transformer.transform(source, result);
                String xmlString = result.getWriter().toString();

                // Mã hóa chuỗi XML
                SecretKey secretKey = AESUtil.generateKey();
                String encryptedXml = AESUtil.encrypt(xmlString, secretKey);
                String keyString = AESUtil.keyToString(secretKey);

                // Ghi khóa mã hóa và nội dung mã hóa vào file
                try (FileWriter fileWriter = new FileWriter(fileToSave)) {
//                    fileWriter.write("Key:" + keyString + "\n");
//                    fileWriter.write("Encrypted XML:" + encryptedXml);
                	
                  fileWriter.write(keyString + "\n");
                  fileWriter.write(encryptedXml);
                }

                System.out.println("File XML đã được mã hóa và lưu tại: " + fileToSave.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static List<Model_Prog> importFromEncryptedXML() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file XML để import");
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
            	String keyLine = reader.readLine();
                String encryptedXmlLine = reader.readLine();
                
//                    String keyString = keyLine.split(":")[1];
//                    String encryptedXml = encryptedXmlLine.split(":")[1];
//                    SecretKey secretKey = AESUtil.getKeyFromString(keyString);
//                    String decryptedXml = AESUtil.decrypt(encryptedXml, secretKey);
                SecretKey secretKey = AESUtil.getKeyFromString(keyLine);
                String decryptedXml = AESUtil.decrypt(encryptedXmlLine, secretKey);    
                List<Model_Prog> modelList = parseXMLString(decryptedXml);
                    return modelList;   
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    private static List<Model_Prog> parseXMLString(String xmlString) {
        List<Model_Prog> modelList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

            NodeList nodeList = doc.getElementsByTagName("model_prog");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                int progId = Integer.parseInt(element.getElementsByTagName("progId").item(0).getTextContent());
                int projectId = Integer.parseInt(element.getElementsByTagName("projectId").item(0).getTextContent());
                String time = element.getElementsByTagName("time").item(0).getTextContent();
                String content = element.getElementsByTagName("content").item(0).getTextContent();

                Model_Prog model = new Model_Prog(progId, projectId, content, time);
                modelList.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelList;
    }

}
