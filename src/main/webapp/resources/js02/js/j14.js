//입력창으로 데이터 입력 받는것 가능.
let age = prompt('나이를 입력 하세요?');

if(age >=19){
 console.log("성인 입니다.");
}else if(age >=13){
  console.log("청소년 입니다.");
}else{
  console.log("어린이 입니다.");
}

console.log(`나이는 ${age} 입니다.`);