
const clickButton=document.querySelector("#btn");
console.log(clickButton);

//이벤트 감지
clickButton.addEventListener('click',function (event) {
  console.log('click');

  //event
  //event.target : 이벤트가 발생한 요소
  console.log('event.target:',event.target);

  console.log('클릭되는 요소 좌표:',event.clientX, event.clientY);
});

// 이벤트 처리 : 가급적 사용 금지
// clickButton.onclick = function(){
//   console.log('click');
// };