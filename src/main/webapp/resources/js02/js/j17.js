console.log(`명시적 형 변환(Explicit Conversion)`);

console.log(Number('123')+1);//124
console.log(parseInt('123')+1);//124

console.log(Number('123.2')+1);//124.2
console.log(parseFloat('123.2')+1);//124.2

console.log(Number(true)+1);//2
console.log(Number(false)+1);//1

console.log('---문자-----------------------');
console.log(String(123)+1);//1231
console.log((123).toString()+1  );//1231


console.log('---boolean-----------------------');
console.log(Boolean(123));//0이 아니면 true
console.log(Boolean(0));//false
console.log(Boolean('good'));//true
console.log(Boolean(''));//false