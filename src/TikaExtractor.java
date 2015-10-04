import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

/**
 * Objective:
 * 1 -- Extract text content of a document out to a text file
 * 2 -- Extract a document's metadata out to a text file (Example of metadata includes author, created_date,  company, content-type, etc)
 * 
 * 
 * @author Linda Liu
 * @version 1.0
 * 
 * Last Updated date: July 21, 2015
 * Note: Currently tested document type includes MS Word, power point, Excel, PDF, and EML w/nested attachment.
 * 
 */
public class TikaExtractor {

	private PrintWriter cwriter;
	private PrintWriter mwriter;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int exit = new TikaExtractor().run(args);
		
		System.exit(exit);

	}

	public int run(String[] args) 
	{

		if (args.length != 2) {
			System.out.println("Usage: TikaExtractor /path/to/mydocument.xlsx /output/path (File type supported Word, PowerPoint, Excel, PDF) ");
			return 0;
		}
		
		String fname = args[0];
		String out_path = args[1];
				
		try {
			File file = new File(fname);
			
			String name = file.getName();
			String out_cname = out_path + "/" + name + ".content.txt";
			String out_mname = out_path + "/" + name + ".metadatat.txt";
						
			ContentHandler handler = new BodyContentHandler();
		    Metadata metadata = new Metadata();
		    InputStream inputstream = new FileInputStream(new File(fname));
		      
		    Parser  parser = new AutoDetectParser(); 
		    ParseContext context = new ParseContext();
		      
		    parser.parse(inputstream, handler, metadata,context);
		    
		    cwriter = new PrintWriter(out_cname, "UTF-8");
		    cwriter.println(handler.toString());
		    
		    mwriter = new PrintWriter(out_mname, "UTF-8");		    
		    String[] metadataNames = metadata.names();
		      
		    for(String nm : metadataNames) {
		         mwriter.println(nm + ": " + metadata.get(nm));
		     }
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			e.printStackTrace();
		} finally {
			cwriter.close();
			mwriter.close();
		}
		
		return  1;
	}
	
}
