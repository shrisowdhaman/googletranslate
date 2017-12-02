package com.shri;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.LanguageListOption;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.common.collect.ImmutableList;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author shrisowdhaman
 * Dec 1, 2017
 */
public class TranslateText {

	public static void detectLanguage(String sourceText, PrintStream out) {
	    Translate translate = createTranslateService();
	    List<Detection> detections = translate.detect(ImmutableList.of(sourceText));
	    System.out.println("Language(s) detected:");
	    for (Detection detection : detections) {
	      out.printf("\t%s\n", detection);
	    }
	  }

	  public static void translateText(String sourceText, PrintStream out) {
	    Translate translate = createTranslateService();
	    Translation translation = translate.translate(sourceText);
	    out.printf("Source Text:\n\t%s\n", sourceText);
	    out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText());
	  }

	  public static void translateTextWithOptionsAndModel(
	      String sourceText,
	      String sourceLang,
	      String targetLang,
	      PrintStream out) {

	    Translate translate = createTranslateService();
	    TranslateOption srcLang = TranslateOption.sourceLanguage(sourceLang);
	    TranslateOption tgtLang = TranslateOption.targetLanguage(targetLang);

	    // Use translate `model` parameter with `base` and `nmt` options.
	    TranslateOption model = TranslateOption.model("nmt");

	    Translation translation = translate.translate(sourceText, srcLang, tgtLang, model);
	    out.printf("Source Text:\n\tLang: %s, Text: %s\n", sourceLang, sourceText);
	    out.printf("TranslatedText:\n\tLang: %s, Text: %s\n", targetLang,
	        translation.getTranslatedText());
	  }

 
	  public static void translateTextWithOptions(
	      String sourceText,
	      String sourceLang,
	      String targetLang,
	      PrintStream out) {

	    Translate translate = createTranslateService();
	    TranslateOption srcLang = TranslateOption.sourceLanguage(sourceLang);
	    TranslateOption tgtLang = TranslateOption.targetLanguage(targetLang);

	    Translation translation = translate.translate(sourceText, srcLang, tgtLang);
	    out.printf("Source Text:\n\tLang: %s, Text: %s\n", sourceLang, sourceText);
	    out.printf("TranslatedText:\n\tLang: %s, Text: %s\n", targetLang,
	        translation.getTranslatedText());
	  }

	  
	  public static void displaySupportedLanguages(PrintStream out, Optional<String> tgtLang) {
	    Translate translate = createTranslateService();
	    LanguageListOption target = LanguageListOption.targetLanguage(tgtLang.orElse("en"));
	    List<Language> languages = translate.listSupportedLanguages(target);

	    for (Language language : languages) {
	      out.printf("Name: %s, Code: %s\n", language.getName(), language.getCode());
	    }
	  }
 
	  public static Translate createTranslateService() {
	    return TranslateOptions.newBuilder().build().getService();
	  }

	  public static void main(String[] args) {
		 
		  String[] options = { "Detect", "Translate", "Langsupport" };

		  JFrame frame = new JFrame("Input Dialog Example 3");
		  String command = (String) JOptionPane.showInputDialog(frame, 
		        "Choose the option",
		        "Detect",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        options, 
		        options[0]);
		    
	    String text;

	    if (command.equals("Detect")) {
	      text = "sample";
	      TranslateText.detectLanguage(text, System.out);
	    } else if (command.equals("Translate")) {
	      text = args[1];
	      try {
	        String sourceLang = "eng";
	        String targetLang = "fr";
	        TranslateText.translateTextWithOptions(text, sourceLang, targetLang, System.out);
	      } catch (ArrayIndexOutOfBoundsException ex) {
	        TranslateText.translateText(text, System.out);
	      }
	    } else if (command.equals("Langsupport")) {
	      try {
	        String target = "sample";
	        TranslateText.displaySupportedLanguages(System.out, Optional.of(target));
	      } catch (ArrayIndexOutOfBoundsException ex) {
	       // TranslateText.displaySupportedLanguages(System.out, Optional.empty());
	      }
	    }
	}

}
