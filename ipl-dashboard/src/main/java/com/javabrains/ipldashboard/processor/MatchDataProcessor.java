package com.javabrains.ipldashboard.processor;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import com.javabrains.ipldashboard.data.MatchInput;
import com.javabrains.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	@Override
	public Match process(final MatchInput matchInput) throws Exception {
		
		Match match = new Match();
		match.setId(Long.parseLong(matchInput.getId()));
		match.setCity(matchInput.getCity());
		match.setDate(LocalDate.parse(matchInput.getDate()));
		match.setPlayerOfMatch(matchInput.getPlayer_of_match());
		match.setVenue(matchInput.getVenue());

		// Set team 1 and team 2 depending on the innings order
		String firstInningTeam, secondInningTeam;

		if ("bat".equals(matchInput.getToss_decision())) {
			firstInningTeam = matchInput.getToss_winner();
			secondInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
					: matchInput.getTeam1();
		} else {
			secondInningTeam = matchInput.getToss_winner();
			firstInningTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
					: matchInput.getTeam1();
		}
		
		match.setTeam1(firstInningTeam);
		match.setTeam2(secondInningTeam);
		match.setTossWinner(matchInput.getToss_winner());
		match.setTossDecision(matchInput.getToss_decision());
		match.setMatchWinner(matchInput.getWinner());
		match.setResult(matchInput.getResult());
		match.setResultMargin(matchInput.getResult_margin());
		match.setUmpire1(matchInput.getUmpire1());
		match.setUmpire2(matchInput.getUmpire2());
		

		return match;
	}

}
