package ua.lviv.footgo.util;

public class Transliterator {

    public static String transliterate(String message){
        char[] abcCyr =   {' ','а','б','в','г','д','е','є','ж','з','и','і','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ь','ю','я','А','Б','В','Г','Д','Е','Є','Ж','З','И','І','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ь','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] abcLat = {" ","a","b","v","g","d","e","ye","zh","z","y","i","j","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch","","ju","ja","A","B","V","G","D","E","Ye","Zh","Z","Y","I","J","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch","","Ju","Ja","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++ ) {
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }
        return builder.toString();
    }
}
