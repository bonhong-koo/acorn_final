function callTwice(callback){
  callback();
  callback();
}

callTwice(function(){
  console.log('두 번 호출!');
});