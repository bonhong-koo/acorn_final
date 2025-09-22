/*
클로저(Closure)

클로저란, 함수가 생성될 때의 외부변수를 기억하고, 함수가 끝난 뒤에도 그 변수에 접근할 수 있는 기능 입니다.
*/

function outer(){
   let count = 0;


   function inner(){
      count++;
      console.log(`count:${count}`);
   } 


   return inner;

}

const counter = outer();
counter();
counter();
counter();
counter();
counter();