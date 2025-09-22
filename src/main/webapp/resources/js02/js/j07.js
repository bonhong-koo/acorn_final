//에러 메시지를 출력. 붉은색으로 표시.
console.error('에러 메시지를 출력');
console.warn('경고 메시지를 출력. 노란색으로 표시.');
console.info('정보 메시지를 출력.');

//객체를 표 형태로 출력
let student = [
   {name:"Alice", score:95},
   {name:"이상무", score:80}
];


console.log(student);
console.log("------------------------");
//데이터를 표 형태로 출력.
console.table(student);
console.log("------------------------");

//객체의 속성을 트리 구조로 표시.
console.dir(student);