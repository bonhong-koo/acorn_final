
let day = prompt('요일 숫자를 입력 하세요?');
console.log(typeof day);//string

//문자열을 int로 변환
let dayInt=parseInt(day);
console.log(typeof dayInt);//number

switch(dayInt){
  case 1:
    console.log(`${dayInt}은 월요일 입니다.`);
  break;
  case 2:
    console.log(`${dayInt}은 화요일 입니다.`);
  break;  
  case 3:
    console.log(`${dayInt}은 수요일 입니다.`);
  break;    
  case 4:
    console.log(`${dayInt}은 목요일 입니다.`);
  break;    
  case 5:
    console.log(`${dayInt}은 금요일 입니다.`);
  break;    
  default:
    console.log(`${dayInt}은 주말 입니다.`);
    break;
}