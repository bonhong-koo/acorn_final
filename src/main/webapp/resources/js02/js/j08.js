console.time('loop');

for(let i = 0;i<100000;i++){
   //작업 수행
   console.log(i);
}

console.timeEnd('loop');