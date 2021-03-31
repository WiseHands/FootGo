package ua.lviv.footgo.jsonmapper;

import ua.lviv.footgo.entity.Card;
import ua.lviv.footgo.entity.Player;

import java.util.Comparator;
import java.util.List;

public class PlayerCards {
    Player player;
    List<Card> cardList;

    public static class SortByCards implements Comparator<PlayerCards>
    {
        public int compare(PlayerCards a, PlayerCards b)
        {
            return b.cardList.size() - a.cardList.size();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public void addCard(Card card) {
        this.cardList.add(card);
    }

    public String formatPosition(Integer index){
        return "" + (1 +index);
    }
}
