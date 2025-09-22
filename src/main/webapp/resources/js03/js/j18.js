const scores = [85,42,77,90];

const highScores = scores.filter( function(score){
  return score>=70;
});


console.log(highScores);
