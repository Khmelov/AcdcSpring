package com.game.controller;

import com.game.entity.Profession;
import com.game.entity.Race;
import lombok.Data;

@Data
public class PlayerInfo {
    public Long id;
    public String name;
    public String title;
    public Race race;
    public Profession profession;
    public Long birthday;
    public Boolean banned;
    public Integer experience;
    public Integer level;
    public Integer untilNextLevel;
}