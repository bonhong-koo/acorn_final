

const box = document.querySelector("#box");
const log = document.querySelector("#log");

console.log(box);
console.log(log);


// box.addEventListener('click',function(event){
//   console.log('box click');
  
//   log.innerHTML += "<p>click 이벤트</p>";
// });

box.addEventListener('dblclick',(e)=>{
  console.log('dblclick');
  
  log.innerHTML += "<p>dblclick</p>";
});

box.addEventListener('mousedown',(e)=>{
  console.log('mousedown');
  
  log.innerHTML += "<p>mousedown</p>";
});

box.addEventListener('mouseover',(e)=>{
  console.log('mouseover');
  
  log.innerHTML += "<p>mouseover</p>";
});

box.addEventListener('mouseout',(e)=>{
  console.log('mouseout');
  
  log.innerHTML += "<p>mouseout</p>";
});