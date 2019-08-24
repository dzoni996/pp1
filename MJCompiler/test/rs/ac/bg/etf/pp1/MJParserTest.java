package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(MJParserTest.class);
		
		Reader br = null;
		try {
			File sourceCode = new File("test/test301.mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(s.value); 
	        
	        // DODATO
	        Tab.init();
	        SemanticAnalyzer.initTypes();
	        
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			//RuleVisitor v = new RuleVisitor();
			SemanticAnalyzer v = new SemanticAnalyzer();
			prog.traverseBottomUp(v); 
	      
			//log.info(" Print count calls = " + v.printCallCount);
			
			//log.info(" Read count calls = " + v.readCallCount);
			
			
			log.info("===================================");
			
			Tab.dump();
			
			String msg;
			if (!v.passed()) {
				if (v.getErrNum() == 1) {
					msg = "Postoji 1 greska u generisanom kodu!";
				} else {
					msg = "Postoje "+v.getErrNum()+" greske u generisanom kodu!";
				}
				log.error(msg);
			} else {
				File objFile = new File("test/program.obj");
				if (objFile.exists()) objFile.delete();
				
				CodeGenerator codeGen = new CodeGenerator();
				prog.traverseBottomUp(codeGen);
				Code.dataSize = v.nVars;
				Code.mainPc = codeGen.getMainPC(); 
				Code.write(new FileOutputStream(objFile));
				
				msg = "Uspesno parsiranje!";
				log.info(msg);
			}

		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
	
	
}
