function percentageForTeamA(teamAValue, teamBValue) {
    sum = teamAValue + teamBValue;
    if (teamAValue <= sum) {
        return Math.round((teamAValue / sum) * 100);
    }
}

function percentageForTeamB(teamAValue, teamBValue) {
    sum = teamAValue + teamBValue;
    if (teamBValue <= sum) {
        return Math.round((teamBValue / sum) * 100);
    }
}

function calculatePercentage(resultForTeamA, resultForTeamB, progressBarForTeamA, progressBarForTeamB) {
    if (resultForTeamA === resultForTeamB) {
        progressBarForTeamA.className = "bar-less";
        progressBarForTeamB.className = "bar-less";
    }

    if (resultForTeamA) {
        progressBarForTeamA.style.width = resultForTeamA + '%';
        if (resultForTeamA > resultForTeamB) {
            progressBarForTeamA.className = "bar-more";
            progressBarForTeamB.className = "bar-less";
        }
    }

    if (resultForTeamB) {
        progressBarForTeamB.style.width = resultForTeamB + '%';
        if (resultForTeamB > resultForTeamA) {
            progressBarForTeamB.className = "bar-more";
            progressBarForTeamA.className = "bar-less";
        }
    }
}
