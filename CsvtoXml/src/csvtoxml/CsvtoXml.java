/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvtoxml;

/**
 *
 * @author H H Fazlul Haque
 */


import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;



import java.util.StringTokenizer;

public class CsvtoXml {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
                String inFile= "C:\\Users\\fazlu\\Downloads\\brdataforbookingsfrom2012to2612.csv";
       
                String outFile = "/E:\\Jony bhai\\outData1.xml";

            // read CSV file using BuferedReader Packages
           try{
         
             
             BufferedReader reader = new BufferedReader(new FileReader(inFile));
            
            //added file for xml build 
            StringBuilder xml = new StringBuilder();
            
            //decliear a variable of line saparator
            String lineBreak = System.getProperty("line.separator");
            String line = null;
            int headercount =1;
            List<String> headers = new ArrayList<String>();
            boolean isHeader = true;
            int count = 0;
            int countlabels = 1;
            int countempty = 0;
            int entryCount = 3;
           
            
            // defination manual print
            xml.append("<brochure>");
            xml.append(lineBreak);            
            xml.append("\t<definition>");
            xml.append(lineBreak);
            xml.append(" \t\t<charactersPerLine>25</charactersPerLine>");
            xml.append(lineBreak);
            xml.append("\t\t<columns>3</columns>");
            xml.append(lineBreak);
            xml.append("\t\t<horizontalSpace>7</horizontalSpace>");
            xml.append(lineBreak);
            xml.append("\t\t<linesPerLabel>8</linesPerLabel>");
            xml.append(lineBreak);
            xml.append("\t\t<marginLeft>3</marginLeft>");
            xml.append(lineBreak);
            xml.append("\t\t<marginTop>3</marginTop>");
            xml.append(lineBreak);
            xml.append("\t\t<rows>7</rows>");
            xml.append(lineBreak);
            xml.append("\t\t<verticalSpace>1</verticalSpace>");
            xml.append(lineBreak);
            xml.append("\t </definition>");
            xml.append(lineBreak);
            
            while ((line = reader.readLine()) != null) {
                 StringTokenizer tokenizer = new StringTokenizer(line, ",");
                
                 if (isHeader) 
                 {
                     isHeader = false;
             
                    while (headercount<=8) 
                       {
                           
                            headers.add(tokenizer.nextToken());
                             
                            headercount++;
                                                    
                        }
                  }
             else
                 {
                    count = 0;
                    headercount=1;
                    if(countlabels==0)
                    {
                    xml.append("\t<labels");
                    xml.append(">");    
                    }

                    xml.append("\t<label");
                    xml.append(">");
                    xml.append(lineBreak);
                    
                    while (headercount<=8)
                        
                        {
                         
                            String[] value = null;
                            int whilecount =0;
                            
                           // value = tokenizer.nextToken();
                            value = line.split(",");
                           
                            if(value[count].equals("\"\"") && (!headers.get(count).equals("line1") && !headers.get(count).equals("line2")))
                            {
                            	xml.append("\t\t<");   
                                xml.append(headers.get(count));
                                xml.append(">");
                                
                                countempty=count;
                                 //System.out.println("count"+count);
                                 //System.out.println("Value"+ value[count]);
                                while(value[countempty].equals("\"\"") && countempty<7)
                                {
                                    countempty++;
                                    
                                    value[count]= value[countempty];
                                    //System.out.println("Value cout"+ value[count]);
                                    value[countempty]= "\"\"";
                                    if(!value[count].equals("\"\""))
                                    {
                                        xml.append(value[count]);
                                    }

                                     whilecount++;
                                     System.out.println("Whilecount"+whilecount);  
                                     System.out.println("Value "+ value[count]);
                                     
                                     
                                     xml.append("</"); 
                                     xml.append(headers.get(count));
                                     xml.append(">");
                                     xml.append(lineBreak);
                                     count++;
                                     headercount++;
                                }
                             
                            }
                            else
                            {
                                     xml.append(value[count]);
                            }
                        
                            //xml.append(value[count]);
                             
                           countempty=count;


                        }
                   
                    xml.append("\t</label>");
                    
                      //label added after 3 , as it is added after every 3  count summ, so mod will be 0
                      //System.out.println(countlabels);
                    if(countlabels%3==0)
                        {
                            xml.append(lineBreak);
                            xml.append("\t</labels");
                            xml.append(">");
                            
                        }
                   xml.append(lineBreak);
                    entryCount++;
                    countlabels++;
                    
                }
                

            }
            xml.append("\t</labels");
            xml.append(">");
            
             //System.out.println(xml.toString());
 
               BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
               writer.write(xml.toString());
               writer.close();  

           }
            catch (Exception e) {
           
            e.printStackTrace();
        }
              
    }

        
    }
    

