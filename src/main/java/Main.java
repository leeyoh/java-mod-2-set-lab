/*
 * Let's extend the library with the following functionality:
 * 
 * 1. In your library, create a set that contains a 
 *      list of each genre that you have a book for
 * 
 * 2. Every time the user adds a book to the library, 
 *      add the book's genre to your new set
 * 
 * 3. Add a new option to your loop to ask the user if 
 *      they want to check if the library has any books of a specific genre
 * 
 * 4. if the user selects that option, ask them for the 
 *      genre they want to check and them let them know if the library has any books of that genre.
 */

 //https://github.com/leeyoh/java-mod-2-set-lab

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Runner.start(sc);
        sc.close();
    }
}
class Runner{
    private static String title, genre;
    private static int pageNum; 

    public static void selection(Scanner sc){
        System.out.println("S = Search, A = Add, G = Genre, [Enter] Quit");
        switch(sc.nextLine().toLowerCase()){
            case "s":
                getBook(sc);
                break;
            case "a":
                addBook(sc);
                break;
            case "g":
                getBooksGenre(sc);
                break;
            default:
                System.exit(0);
                return;
        }
    }
    public static void getBook(Scanner sc){
        System.out.print("Title: ");
        Library.getBook(sc.nextLine());
    }
    public static void getBooksGenre(Scanner sc){
        System.out.print("Genre: ");
        Library.getBooksGenre(sc.nextLine());
        
    }
    public static void addBook(Scanner sc){
        String[][] userPrompts = {
            {"title", "Book Title: "},
            {"genre","Book Genre: "},
            {"pageNum", "Number of Pages: "},
        };
        String input;
        for(String[] prompt: userPrompts){
            System.out.print(prompt[1]);
            input = sc.nextLine();
            if(input.equals("")){
                return;
            }
            switch(prompt[0]){
                case "title":
                    title = input;
                    break;
                case "genre":
                    genre = input;
                    break;
                case "pageNum":
                    pageNum = Integer.parseInt(input);
                    break;
            }
        }
        Book book = new Book(title,genre,pageNum);
        Library.addBook(book);
    }
    
    public static void start(Scanner sc){
        while(true){
            selection(sc);
        }
    }
}

class Library {
    static Map<String,Book> books = new HashMap<String,Book>();
    static Map<String,List<Book>> booksGenre = new HashMap<String,List<Book>>();
    static Set<String> genres = new HashSet<String>();
    
    public static void addBook(Book book){
        if(books.containsKey(book.title)){
            System.out.println("Book Already Exists");
            return;
        }
        books.put(book.title,book);
        genres.add(book.genre);

        List<Book> booklist = new ArrayList<Book>();
        if(booksGenre.get(book.genre) != null){
            booklist = booksGenre.get(book.genre);
        }
        booklist.add(book);
        booksGenre.put(book.genre, booklist);

        System.out.println("Added Book " + book);
        System.out.println("Collection: " + books);
        System.out.println("Genre: " + genres);
    }

    public static void getBook(String title){
        try{
            System.out.println("Book Found: " + books.get(title));
        } catch (Exception e ){
            System.out.println("Book doesn't exist");
        }
    }

    public static void getBooksGenre(String genre){
        System.out.println(booksGenre.get(genre));
    }
}

class Book {
    public String title,genre;
    private int numPages;
    public Book(String title, String genre, int numPages){
        this.title = title;
        this.genre = genre;
        this.numPages = numPages;
    }
    @Override
    public String toString(){
        return "Title: " + this.title + " Genre: " + this.genre + " Pages: " + this.numPages;
    }
}