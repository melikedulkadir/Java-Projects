import java.io.*;
import java.util.*;

public class JournalOperations {
    // Functions for array and arraylist to check if the given article exists.

    public boolean doesExist(ArrayList<Article> articles, String data) {
        for (Article article : articles) {
            if (article.getPaperid() == Integer.parseInt(data)) {
                return false;
            }
        }
        return true;
    }

    public boolean doesExistArray(int[] articleArray, int data) {
        for (int articleId : articleArray) {
            if (articleId == data) {
                return false;
            }
        }
        return true;

    }

    // Function for setting info to certain article
    public void readArticle(String articleFileName, ArrayList<Article> articles) throws FileNotFoundException {
        Scanner articleFile = new Scanner(new File(articleFileName));
        while (articleFile.hasNextLine()) {
            String articleInfo = articleFile.nextLine();
            String[] articlesData = articleInfo.split("\\s+");

            if (doesExist(articles, articlesData[1])) {
                articles.add(new Article(Integer.parseInt(articlesData[1])));
            }
            for (Article article : articles) {
                if (article.getPaperid() == Integer.parseInt(articlesData[1])) {
                    article.setName(articlesData[2]);
                    article.setPublisherName(articlesData[3]);
                    article.setPublishYear(Integer.parseInt(articlesData[4]));
                }
            }
        }
    }

    // Listing function (with sorting info (flag))
    public void listMethod(FileWriter output, ArrayList<Author> authors, ArrayList<Article> articles, boolean flag) throws IOException {
        for (Author author : authors) {
            if (flag){
            Arrays.sort(author.getArticleArray());
            }
            output.write("Author:" + author.getId() + "\t" + author.getName() + "\t" + author.getUniversity() + "\t" + author.getDepartment() + "\t" + author.getEmail() + "\n");
            for (int articleId : author.getArticleArray()) {
                if (articleId != 0) {
                    for (Article article : articles) {
                        if (article.getPaperid() == articleId) {
                            output.write("+" + article.getPaperid() + "\t" + article.getName() + "\t" + article.getPublisherName() + "\t" + article.getPublishYear() + "\n");
                        }
                    }
                }
            }
            output.write("\n");
        }
    }


    public void completeAll(ArrayList<Author> authors, ArrayList<Article> articles) {

        for (Author author : authors) {
            int count = 0;
            for (int articleId : author.getArticleArray()) {
                if (articleId == 0) {
                    count++;
                }
            }

            if (count > 0) {
                for (Article article : articles) {
                    if ((Integer.toString(article.getPaperid()).substring(0, 3)).equals(Integer.toString(author.getId()))) {
                        if (doesExistArray(author.getArticleArray(), article.getPaperid())) {
                            author.getArticleArray()[5 - count] = article.getPaperid();
                            count--;
                        }
                    }
                }
            }
        }
    }

    public void del(ArrayList<Author> authors, int id){
        authors.removeIf(author -> author.getId() == id);
    }
}

