import java.io.*;
import java.util.*;

class fileThing{
    public static void createFile() throws Exception{
        try {
            File myObj = new File("preguntas.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists, Path: "+myObj.getPath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void readFile() throws Exception{
        try {
            File myObj = new File("preguntas.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Scanner textReader = new Scanner(data).useDelimiter("/");
                String text = textReader.next();
                int number1 = textReader.nextInt();
                int number2 = textReader.nextInt();
                System.out.println(text+" "+(number1+number2));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void writeFile() throws Exception{
        try {
            FileWriter myWriter = new FileWriter("texto.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
class pregunta {
    public String descripcionPregunta;
    public String[] respuestas = new String[8];
    public int respuestaCorrecta;
    public boolean[] tipo = new boolean[2];

    public void pregunta() {

    }

    public pregunta createPregunta(ArrayList<pregunta> listaPreguntas) throws Exception {
        //Crear la pregunta
        pregunta preguntaRegresar = new pregunta();
        try {
            File myObj = new File("preguntas.txt");
            Scanner myReader = new Scanner(myObj);
            //while (myReader.hasNextLine()) {
                for (int i = 0; i < listaPreguntas.size(); i++){
                    myReader.nextLine();
                }
                String data = myReader.nextLine();
                Scanner textReader = new Scanner(data).useDelimiter("@@@");
                //Pregunta
                preguntaRegresar.descripcionPregunta = textReader.next();
                //Respuestas
                for (int i = 0; i < 8; i++) {
                    preguntaRegresar.respuestas[i] = textReader.next();
                }
                //Respuesta correcta
                preguntaRegresar.respuestaCorrecta = textReader.nextInt();
                //tipo de pregunta
                preguntaRegresar.tipo[0] = textReader.nextBoolean();
                preguntaRegresar.tipo[1] = textReader.nextBoolean();
            //}
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return preguntaRegresar;
    }
    public int cantidadPreguntas() throws Exception {
        int numberLines = 0;
        try {
            File myObj = new File("preguntas.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                myReader.nextLine();
                numberLines += 1;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return numberLines;
    }
}
public class Main {
    public static void main(String[] args) throws Exception{
        ArrayList<pregunta> preguntas = new ArrayList<>();
        pregunta preguntasDraft = new pregunta();
        for (int i = 0; i < preguntasDraft.cantidadPreguntas(); i++){
            preguntas.add(preguntasDraft.createPregunta(preguntas));
        }
        for (int i = 0; i < 8; i++){
            System.out.println(preguntas.get(0).respuestas[i]);

        }
    }
}