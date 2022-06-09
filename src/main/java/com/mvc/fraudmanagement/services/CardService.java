package com.mvc.fraudmanagement.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvc.fraudmanagement.entities.Card;
import com.mvc.fraudmanagement.repos.CardRepository;

@Service
public class CardService {
	private List<Card> cardList = new ArrayList<>();
	
	@Autowired
	public CardRepository cardRepository;

	public void getAllCard() {
		cardList = cardRepository.findAll();
	}

	public void save(Card card) {
		cardRepository.save(card);
	}

	public void delete(Card card) {
		cardRepository.delete(card);
	}

	public Card getCardById(int id) {
		getAllCard();
		for (Card card : cardList) {
			if (card.getId() == id) {
				return card;
			}
		}
		return null;
	}

	public Card deleteCard(String id) {
		Card card = getCardByUserId(id);
		if (card.getUserId().equals(id)) {
			return card;
		} else {
			return null;
		}
	}

	public List<Card> showAllCardFraud() {
		List<Card> cards = new ArrayList<>();
		getAllCard();
		for (Card c : cardList) {
			cards.add(c);
		}
		return cards;
	}

	public Card getCardByUserId(String id) {
		getAllCard();
		for (Card card : cardList) {
			if (card.getUserId().equals(id)) {
				return card;
			}
		}
		return null;
	}
}