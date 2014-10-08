package edu.cmu.deiis.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

import type.UIMATypeEnum;

public class CollectionReader extends CollectionReader_ImplBase {

  private Scanner scanner;

  @Override
  /**
   * Initialize a scanner object to read the file, which is parameterized with 
   * a UIMATypeEnum. 
   */
  public void initialize() throws ResourceInitializationException {
    File file = new File(getConfigParameterValue(UIMATypeEnum.INPUT_FILE.getParam()).toString());
    try {
      this.scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  /**
   * Get the next value in the scanner and add it to the jCas
   */
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    try {
      JCas jCas = aCAS.getJCas();
      if (this.hasNext()) {
        String line = this.scanner.next();
        String[] data = line.split(" ",2);
        Sentence sentence = new Sentence(jCas); 
        sentence.setSentenceId(data[0]);
        sentence.setSentenceText(data[1]);
        sentence.addToIndexes();
      }
    } catch (CASException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public boolean hasNext() throws IOException, CollectionException {
    return this.scanner.hasNext();
  }

  @Override
  public Progress[] getProgress() {
    return new Progress[0];
  }

  @Override
  public void close() throws IOException {
    this.scanner.close();
  }

}
