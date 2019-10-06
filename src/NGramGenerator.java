import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class NGramGenerator {

    public static void main(String[] args) {

        long starTime = System.nanoTime();

        long counter = 1;
        String input = args[0];
        //System.out.println(input);
        String output = args[1];
        //System.out.println(output);
        try{

            Stream<Path> files = Files.list(Paths.get(input)); //return the number of books
            //Stream<Path> files = Files.list(Paths.get("/home/michela/IdeaProjects/ParallelNGramGenerator/prova"));
            counter = files.count();

            File fileOut2 = new File(output+"/output2");
            FileWriter fw2 = new FileWriter(fileOut2);
            BufferedWriter bw2 = new BufferedWriter(fw2);

            File fileOut3 = new File(output+"/output3");
            FileWriter fw3 = new FileWriter(fileOut3);
            BufferedWriter bw3 = new BufferedWriter(fw3);


            int j=1;
            int y=1;
        try{
            for(int i=1;i<=counter; i++) {
                File file = new File(input + "/"+i);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;

                ArrayList<String> ngrams2 = new ArrayList<String>();
                ArrayList<String> ngrams3 = new ArrayList<String>();

                while ((st = br.readLine()) != null) {
                    st = st.replace(" ", "");
                    //create 2-gram
                    for (int k = 0; k <= st.length() - 2; k++) {
                        ngrams2.add(st.substring(k, k + 2).trim());
                    }
                    //create 3-gram

                    for (int k = 0; k <= st.length() - 3; k++) {
                        ngrams3.add(st.substring(k, k + 3).trim());
                    }

                }
                for (int k=0; k < ngrams2.size();k++){
                    bw2.write(ngrams2.get(k)+",");
                    //bw.write(" ");
                    if(j%60==0) {
                        bw2.newLine();
                    }
                    j++;
                    //bw.write(System.getProperty( "line.separator" ));
                }
                for (int k=0; k < ngrams3.size();k++){
                    bw3.write(ngrams3.get(k)+",");
                    //bw.write(" ");
                    if(y%40==0) {
                        bw3.newLine();
                    }
                    y++;
                }

            }
        }catch (Exception e){
            System.out.println("Elaboration error");
        }
        }catch (Exception e){
            System.out.println("Database initialization error");
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - starTime;
        System.out.println(totalTime);
    }}