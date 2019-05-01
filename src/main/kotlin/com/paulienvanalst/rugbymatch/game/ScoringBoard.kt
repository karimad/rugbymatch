package com.paulienvanalst.rugbymatch.game

import com.paulienvanalst.rugbymatch.events.ScoringEvent
import com.paulienvanalst.rugbymatch.events.StartGame
import com.paulienvanalst.rugbymatch.team.NotImplementedException
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
open class ScoringBoard {

    private var scoringHistory : List<Score> = emptyList()
    private lateinit var hostingTeam : TeamName
    private lateinit var visitingTeam: TeamName

    @EventListener
    fun start(startGame: StartGame) {
        hostingTeam = startGame.hostingTeam
        visitingTeam = startGame.visitingTeam
    }

    @EventListener
    fun scoring(scoringEvent: ScoringEvent) {
        scoringHistory += Score(scoringEvent.team, scoringEvent.type)
    }



    fun currentScore(): GameScore {
        val scoreHosting = scoringHistory.groupBy { score: Score -> score.forTeam }
        val hostScore = scoreHosting[hostingTeam]?.sumBy { score -> score.type.points } ?: 0
        val visitScore = scoreHosting[visitingTeam]?.sumBy { score -> score.type.points } ?: 0
        return GameScore(hostingTeam to hostScore, visitingTeam to visitScore)
    }
    fun clear () {
        scoringHistory = emptyList()

    }
}


