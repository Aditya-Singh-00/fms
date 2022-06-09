package com.mvc.fraudmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.mvc.fraudmanagement.entities.Card;
import com.mvc.fraudmanagement.services.CardService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

@Controller
@SessionAttributes({ "card", "user" })
public class CardController {

	@Autowired
	private CardService cardService;

	@RequestMapping(value = "/card-fraud", method = RequestMethod.GET)
	public String showCard() {
		return "fraud-pages/card-fraud";
	}

	@RequestMapping(value = "/card-registration", method = RequestMethod.GET)
	public String showCardRegistration(ModelMap model) {
		model.addAttribute("card", new Card());
		return "registration-forms/card-registration";
	}

	@RequestMapping(value = "/card-registration", method = RequestMethod.POST)
	public String saveCardRegistration(ModelMap model, @Valid Card card, BindingResult result) {

		if (result.hasErrors()) {
			return "registration-forms/card-registration";
		}

		cardService.save(card);

		return "redirect:/card-fraud";
	}

	@RequestMapping(value = "/card-show", method = RequestMethod.GET)
	public String viewAllCard(ModelMap model, @RequestParam String id) {
		Card card = cardService.getCardByUserId(id);
		if (card instanceof Card) {
			model.put("card", card);
		} else {
			model.put("errorMessage", "Card does not exist");
		}
		return "dashboards/card-dashboard";
	}

	@RequestMapping(value = "/card-delete", method = RequestMethod.GET)
	public String deleteCard(@RequestParam String id) {
		Card card=cardService.deleteCard(id);
		cardService.delete(card);
		return "redirect:/card-fraud";
	}
}