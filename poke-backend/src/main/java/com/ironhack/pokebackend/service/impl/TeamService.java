package com.ironhack.pokebackend.service.impl;

import com.ironhack.pokebackend.model.Team;
import com.ironhack.pokebackend.repository.TeamRepository;
import com.ironhack.pokebackend.service.interfaces.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeamService implements ITeamService {

    // Inject repository

    @Autowired
    private TeamRepository teamRepository;

    // Add pokemon service

    public void addPokemon(Integer id, Integer pokemonId) {
        Team team;

        // We first check if the team exist, in negative case it throws an exception

        if(teamRepository.existsById(id)) {
            team = teamRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }

        /* To add a new pokemon to a team we must check if there is any spare space. In case the team
        is full we throw an exception and if there is enough space we add the pokemon and save the team */

        if(team.getPokemon7() == null) {

            if(team.getPokemon1() == null) {
                team.setPokemon1(pokemonId);
                teamRepository.save(team);
            } else if(team.getPokemon2() == null) {
                team.setPokemon2(pokemonId);
                teamRepository.save(team);
            } else if(team.getPokemon3() == null) {
                team.setPokemon3(pokemonId);
                teamRepository.save(team);
            } else if(team.getPokemon4() == null) {
                team.setPokemon4(pokemonId);
                teamRepository.save(team);
            } else if(team.getPokemon5() == null) {
                team.setPokemon5(pokemonId);
                teamRepository.save(team);
            } else if(team.getPokemon6() == null) {
                team.setPokemon6(pokemonId);
                teamRepository.save(team);
            } else {
                team.setPokemon7(pokemonId);
                teamRepository.save(team);
            }

        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The team has already 7 Pokemon");
        }
    }

    // Delete pokemon from a team service

    public void deletePokemon(Integer id, Integer pokemonPosition) {
        Team team;

        // We first must check if the team exists, in negative case we throw an exception

        if(teamRepository.existsById(id)) {
            team = teamRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }

        /* Since the maximum number of pokemon in a team is equal to 7, we must verify that the pokemon
           to be eliminated belongs to this rank */

        if(pokemonPosition < 1 || pokemonPosition > 7) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Pokemon position must be between 1 and 7");
        }

        /* If the pokemon to be deleted belongs to the rank, we reorder the pokemon positions, delete the
            pokemon and save the new team */

        if(pokemonPosition == 1) {
            team.setPokemon1(team.getPokemon2());
            team.setPokemon2(team.getPokemon3());
            team.setPokemon3(team.getPokemon4());
            team.setPokemon4(team.getPokemon5());
            team.setPokemon5(team.getPokemon6());
            team.setPokemon6(team.getPokemon7());
            team.setPokemon7(null);
        } else if(pokemonPosition == 2) {
            team.setPokemon2(team.getPokemon3());
            team.setPokemon3(team.getPokemon4());
            team.setPokemon4(team.getPokemon5());
            team.setPokemon5(team.getPokemon6());
            team.setPokemon6(team.getPokemon7());
            team.setPokemon7(null);
        } else if(pokemonPosition == 3) {
            team.setPokemon3(team.getPokemon4());
            team.setPokemon4(team.getPokemon5());
            team.setPokemon5(team.getPokemon6());
            team.setPokemon6(team.getPokemon7());
            team.setPokemon7(null);
        } else if(pokemonPosition == 4) {
            team.setPokemon4(team.getPokemon5());
            team.setPokemon5(team.getPokemon6());
            team.setPokemon6(team.getPokemon7());
            team.setPokemon7(null);
        } else if(pokemonPosition == 5) {
            team.setPokemon5(team.getPokemon6());
            team.setPokemon6(team.getPokemon7());
            team.setPokemon7(null);
        } else if(pokemonPosition == 6) {
            team.setPokemon6(team.getPokemon7());
            team.setPokemon7(null);
        } else if(pokemonPosition == 7) {
            team.setPokemon7(null);
        }
        teamRepository.save(team);
    }
}
