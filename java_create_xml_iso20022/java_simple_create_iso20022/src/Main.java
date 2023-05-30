import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Main {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Create the root element for Document
            Element document = doc.createElement("Document");
            document.setAttribute("xmlns", "urn:iso:std:iso:20022:tech:xsd:pain.001.001.03");
            doc.appendChild(document);

            // Create the CstmrCdtTrfInitn element
            Element cstmrCdtTrfInitn = doc.createElement("CstmrCdtTrfInitn");
            document.appendChild(cstmrCdtTrfInitn);

            // Create the GrpHdr element
            Element grpHdr = doc.createElement("GrpHdr");
            cstmrCdtTrfInitn.appendChild(grpHdr);

            Element msgId = createXmlElement(doc, "MsgId", "123456789");
            grpHdr.appendChild(msgId);

            Element creDtTm = createXmlElement(doc, "CreDtTm", "2023-05-30T10:30:00");
            grpHdr.appendChild(creDtTm);

            Element initgPty = doc.createElement("InitgPty");
            grpHdr.appendChild(initgPty);

            Element nm = createXmlElement(doc, "Nm", "John Doe");
            initgPty.appendChild(nm);

            Element id = doc.createElement("Id");
            initgPty.appendChild(id);

            Element orgId = doc.createElement("OrgId");
            id.appendChild(orgId);

            Element othr = doc.createElement("Othr");
            orgId.appendChild(othr);

            Element orgIdValue = createXmlElement(doc, "Id", "CompanyA");
            othr.appendChild(orgIdValue);

            // Create the PmtInf element
            Element pmtInf = doc.createElement("PmtInf");
            cstmrCdtTrfInitn.appendChild(pmtInf);

            Element pmtInfId = createXmlElement(doc, "PmtInfId", "ABC123");
            pmtInf.appendChild(pmtInfId);

            Element pmtMtd = createXmlElement(doc, "PmtMtd", "TRF");
            pmtInf.appendChild(pmtMtd);

            Element reqdExctnDt = createXmlElement(doc, "ReqdExctnDt", "2023-06-01");
            pmtInf.appendChild(reqdExctnDt);

            Element dbtr = doc.createElement("Dbtr");
            pmtInf.appendChild(dbtr);

            Element dbtrNm = createXmlElement(doc, "Nm", "CompanyA");
            dbtr.appendChild(dbtrNm);

            Element dbtrAcct = doc.createElement("DbtrAcct");
            pmtInf.appendChild(dbtrAcct);

            Element dbtrAcctId = doc.createElement("Id");
            dbtrAcct.appendChild(dbtrAcctId);

            Element dbtrAcctIBAN = createXmlElement(doc, "IBAN", "GB29NWBK60161331926819");
            dbtrAcctId.appendChild(dbtrAcctIBAN);

            Element dbtrAgt = doc.createElement("DbtrAgt");
            pmtInf.appendChild(dbtrAgt);

            Element finInstnId = doc.createElement("FinInstnId");
            dbtrAgt.appendChild(finInstnId);

            Element bic = createXmlElement(doc, "BIC", "ABCDUS33");
            finInstnId.appendChild(bic);

            Element cdtTrfTxInf = doc.createElement("CdtTrfTxInf");
            pmtInf.appendChild(cdtTrfTxInf);

            Element pmtId = doc.createElement("PmtId");
            cdtTrfTxInf.appendChild(pmtId);

            Element endToEndId = createXmlElement(doc, "EndToEndId", "123456789");
            pmtId.appendChild(endToEndId);

            Element amt = doc.createElement("Amt");
            cdtTrfTxInf.appendChild(amt);

            Element instdAmt = doc.createElement("InstdAmt");
            instdAmt.setAttribute("Ccy", "USD");
            instdAmt.appendChild(doc.createTextNode("1000.00"));
            amt.appendChild(instdAmt);

            Element cdtr = doc.createElement("Cdtr");
            cdtTrfTxInf.appendChild(cdtr);

            Element cdtrNm = createXmlElement(doc, "Nm", "John Smith");
            cdtr.appendChild(cdtrNm);

            Element cdtrAcct = doc.createElement("CdtrAcct");
            cdtTrfTxInf.appendChild(cdtrAcct);

            Element cdtrAcctId = doc.createElement("Id");
            cdtrAcct.appendChild(cdtrAcctId);

            Element cdtrAcctIBAN = createXmlElement(doc, "IBAN", "GB08BARC20014755766504");
            cdtrAcctId.appendChild(cdtrAcctIBAN);

            Element rmtInf = doc.createElement("RmtInf");
            cdtTrfTxInf.appendChild(rmtInf);

            Element ustrd = createXmlElement(doc, "Ustrd", "Payment for services rendered");
            rmtInf.appendChild(ustrd);

            // Write the XML document to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult("payment.xml");

            transformer.transform(source, result);

            System.out.println("Payment XML file created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Element createXmlElement(Document doc, String elementName, String elementValue) {
        Element element = doc.createElement(elementName);
        element.appendChild(doc.createTextNode(elementValue));
        return element;
    }
}