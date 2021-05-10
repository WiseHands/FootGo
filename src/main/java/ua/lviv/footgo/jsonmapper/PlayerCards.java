package ua.lviv.footgo.jsonmapper;

import ua.lviv.footgo.entity.Card;
import ua.lviv.footgo.entity.Player;

import java.util.Comparator;
import java.util.List;

public class PlayerCards {
    Player player;
    List<Card> yellowCardList;
    List<Card> redCardList;

    public static class SortByCards implements Comparator<PlayerCards> {
        public int compare(PlayerCards a, PlayerCards b) {
            return (b.yellowCardList.size()+b.redCardList.size()) - (a.yellowCardList.size()+a.redCardList.size());
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getYellowCardList() {
        return yellowCardList;
    }

    public void setYellowCardList(List<Card> yellowCardList) {
        this.yellowCardList = yellowCardList;
    }

    public void addYellowCard(Card card) {
        this.yellowCardList.add(card);
    }

    public void addRedCard(Card card) {
        this.redCardList.add(card);
    }

    public List<Card> getRedCardList() {
        return redCardList;
    }

    public void setRedCardList(List<Card> redCardList) {
        this.redCardList = redCardList;
    }

    public String formatPosition(Integer index){
        return "" + (1 +index);
    }
}
