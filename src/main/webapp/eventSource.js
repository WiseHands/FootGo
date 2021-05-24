let sse = new EventSource('/sse?' + 'gameId=' + gameId);

const teamAStrikesData = document.getElementById('teamAStrikesData');
const teamBStrikesData = document.getElementById('teamBStrikesData');

const teamAHitsTheTargetData = document.getElementById('teamAHitsTheTargetData');
const teamBHitsTheTargetData = document.getElementById('teamBHitsTheTargetData');

const teamAStrikesPastTheGateData = document.getElementById('teamAStrikesPastTheGateData');
const teamBStrikesPastTheGateData = document.getElementById('teamBStrikesPastTheGateData');

const teamAFreeKicksData = document.getElementById('teamAFreeKicksData');
const teamBFreeKicksData = document.getElementById('teamBFreeKicksData');

const teamAFoulsData = document.getElementById('teamAFoulsData');
const teamBFoulsData = document.getElementById('teamBFoulsData');

const teamACornerKicksData = document.getElementById('teamACornerKicksData');
const teamBCornerKicksData = document.getElementById('teamBCornerKicksData');

const teamAGoals = document.getElementById('teamAGoals');
const teamBGoals = document.getElementById('teamBGoals');

const teamAPenalty = document.getElementById('teamAPenalty');
const teamBPenalty = document.getElementById('teamBPenalty');

sse.addEventListener("teamAStrikes", function(evt) {
   let data = JSON.parse(evt.data);
   teamAStrikesData.innerHTML = data;

   let strikesForTeamA = percentageForTeamA(parseInt(teamAStrikesData.innerHTML, 10), parseInt(teamBStrikesData.innerHTML, 10));
   let strikesForTeamB = percentageForTeamB(parseInt(teamAStrikesData.innerHTML, 10), parseInt(teamBStrikesData.innerHTML, 10));
   calculatePercentage(strikesForTeamA, strikesForTeamB, strikesTeamA, strikesTeamB);
});

sse.addEventListener("teamBStrikes", function(evt) {
   let data = JSON.parse(evt.data);
   teamBStrikesData.innerHTML = data;

   let strikesForTeamA = percentageForTeamA(parseInt(teamAStrikesData.innerHTML, 10), parseInt(teamBStrikesData.innerHTML, 10));
   let strikesForTeamB = percentageForTeamB(parseInt(teamAStrikesData.innerHTML, 10), parseInt(teamBStrikesData.innerHTML, 10));
   calculatePercentage(strikesForTeamA, strikesForTeamB, strikesTeamA, strikesTeamB);
});

sse.addEventListener("teamAHitsTheTarget", function(evt) {
   let data = JSON.parse(evt.data);
   teamAHitsTheTargetData.innerHTML = data;

   let hitsTheTargetForTeamA = percentageForTeamA(parseInt(teamAHitsTheTargetData.innerHTML, 10), parseInt(teamBHitsTheTargetData.innerHTML, 10));
   let hitsTheTargetForTeamB = percentageForTeamB(parseInt(teamAHitsTheTargetData.innerHTML, 10), parseInt(teamBHitsTheTargetData.innerHTML, 10));
   calculatePercentage(hitsTheTargetForTeamA, hitsTheTargetForTeamB, hitsTheTargetTeamA, hitsTheTargetTeamB);
});

sse.addEventListener("teamBHitsTheTarget", function(evt) {
   let data = JSON.parse(evt.data);
   teamBHitsTheTargetData.innerHTML = data;

   let hitsTheTargetForTeamA = percentageForTeamA(parseInt(teamAHitsTheTargetData.innerHTML, 10), parseInt(teamBHitsTheTargetData.innerHTML, 10));
   let hitsTheTargetForTeamB = percentageForTeamB(parseInt(teamAHitsTheTargetData.innerHTML, 10), parseInt(teamBHitsTheTargetData.innerHTML, 10));
   calculatePercentage(hitsTheTargetForTeamA, hitsTheTargetForTeamB, hitsTheTargetTeamA, hitsTheTargetTeamB);
});

sse.addEventListener("teamAStrikesPastTheGate", function(evt) {
   let data = JSON.parse(evt.data);
   teamAStrikesPastTheGateData.innerHTML = data;

   let strikesPastTheGateForTeamA = percentageForTeamA(parseInt(teamAStrikesPastTheGateData.innerHTML, 10), parseInt(teamBStrikesPastTheGateData.innerHTML, 10));
   let strikesPastTheGateForTeamB = percentageForTeamB(parseInt(teamAStrikesPastTheGateData.innerHTML, 10), parseInt(teamBStrikesPastTheGateData.innerHTML, 10));
   calculatePercentage(strikesPastTheGateForTeamA, strikesPastTheGateForTeamB, strikesPastTheGateTeamA, strikesPastTheGateTeamB);
});

sse.addEventListener("teamBStrikesPastTheGate", function(evt) {
   let data = JSON.parse(evt.data);
   teamBStrikesPastTheGateData.innerHTML = data;

   let strikesPastTheGateForTeamA = percentageForTeamA(parseInt(teamAStrikesPastTheGateData.innerHTML, 10), parseInt(teamBStrikesPastTheGateData.innerHTML, 10));
   let strikesPastTheGateForTeamB = percentageForTeamB(parseInt(teamAStrikesPastTheGateData.innerHTML, 10), parseInt(teamBStrikesPastTheGateData.innerHTML, 10));
   calculatePercentage(strikesPastTheGateForTeamA, strikesPastTheGateForTeamB, strikesPastTheGateTeamA, strikesPastTheGateTeamB);
});

sse.addEventListener("teamAFreeKicks", function(evt) {
   let data = JSON.parse(evt.data);
   teamAFreeKicksData.innerHTML = data;

   let freeKicksForTeamA = percentageForTeamA(parseInt(teamAFreeKicksData.innerHTML, 10), parseInt(teamBFreeKicksData.innerHTML, 10));
   let freeKicksForTeamB = percentageForTeamB(parseInt(teamAFreeKicksData.innerHTML, 10), parseInt(teamBFreeKicksData.innerHTML, 10));
   calculatePercentage(freeKicksForTeamA, freeKicksForTeamB, freeKicksTeamA, freeKicksTeamB);
});

sse.addEventListener("teamBFreeKicks", function(evt) {
   let data = JSON.parse(evt.data);
   teamBFreeKicksData.innerHTML = data;

   let freeKicksForTeamA = percentageForTeamA(parseInt(teamAFreeKicksData.innerHTML, 10), parseInt(teamBFreeKicksData.innerHTML, 10));
   let freeKicksForTeamB = percentageForTeamB(parseInt(teamAFreeKicksData.innerHTML, 10), parseInt(teamBFreeKicksData.innerHTML, 10));
   calculatePercentage(freeKicksForTeamA, freeKicksForTeamB, freeKicksTeamA, freeKicksTeamB);
});

sse.addEventListener("teamAFouls", function(evt) {
   let data = JSON.parse(evt.data);
   teamAFoulsData.innerHTML = data;

   let foulsForTeamA = percentageForTeamA(parseInt(teamAFoulsData.innerHTML, 10), parseInt(teamBFoulsData.innerHTML, 10));
   let foulsForTeamB = percentageForTeamB(parseInt(teamAFoulsData.innerHTML, 10), parseInt(teamBFoulsData.innerHTML, 10));
   calculatePercentage(foulsForTeamA, foulsForTeamB, foulsTeamA, foulsTeamB);
});

sse.addEventListener("teamBFouls", function(evt) {
   let data = JSON.parse(evt.data);
   teamBFoulsData.innerHTML = data;

   let foulsForTeamA = percentageForTeamA(parseInt(teamAFoulsData.innerHTML, 10), parseInt(teamBFoulsData.innerHTML, 10));
   let foulsForTeamB = percentageForTeamB(parseInt(teamAFoulsData.innerHTML, 10), parseInt(teamBFoulsData.innerHTML, 10));
   calculatePercentage(foulsForTeamA, foulsForTeamB, foulsTeamA, foulsTeamB);
});

sse.addEventListener("teamACornerKicks", function(evt) {
   let data = JSON.parse(evt.data);
   teamACornerKicksData.innerHTML = data;

   let cornerKicksForTeamA = percentageForTeamA(parseInt(teamACornerKicksData.innerHTML, 10), parseInt(teamBCornerKicksData.innerHTML, 10));
   let cornerKicksForTeamB = percentageForTeamB(parseInt(teamACornerKicksData.innerHTML, 10), parseInt(teamBCornerKicksData.innerHTML, 10));
   calculatePercentage(cornerKicksForTeamA, cornerKicksForTeamB, cornerKicksTeamA, cornerKicksTeamB);
});

sse.addEventListener("teamBCornerKicks", function(evt) {
   let data = JSON.parse(evt.data);
   teamBCornerKicksData.innerHTML = data;

   let cornerKicksForTeamA = percentageForTeamA(parseInt(teamACornerKicksData.innerHTML, 10), parseInt(teamBCornerKicksData.innerHTML, 10));
   let cornerKicksForTeamB = percentageForTeamB(parseInt(teamACornerKicksData.innerHTML, 10), parseInt(teamBCornerKicksData.innerHTML, 10));
   calculatePercentage(cornerKicksForTeamA, cornerKicksForTeamB, cornerKicksTeamA, cornerKicksTeamB);
});

if (teamAGoals && teamBGoals) {
    sse.addEventListener("teamAGoals", function(evt) {
        let data = JSON.parse(evt.data);
        teamAGoals.innerHTML = data;
    });
    sse.addEventListener("teamBGoals", function(evt) {
        let data = JSON.parse(evt.data);
        teamBGoals.innerHTML = data;
    });
}

if (teamAPenalty && teamBPenalty) {
    sse.addEventListener("teamAPenalty", function(evt) {
        let data = JSON.parse(evt.data);
        teamAPenalty.innerHTML = '(' + data + ')';
    });
    sse.addEventListener("teamBPenalty", function(evt) {
        let data = JSON.parse(evt.data);
        teamBPenalty.innerHTML = '(' + data + ')';
    });
}