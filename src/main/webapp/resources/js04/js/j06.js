

const popupButton = document.querySelector("#popUpBtn");
console.log(popupButton);

popupButton.addEventListener('click',function(event){
  console.log('click');
  //let url = "https://cafe.daum.net/pcwk";
  
  const screenWidth = window.screen.width;
  const screenHeight = window.screen.height;

  console.log('screenWidth:'+screenWidth);
  console.log('screenHeight:'+screenHeight);

  const left=(screenWidth - 800)/2;
  const top=(screenHeight - 600)/2;


  let url = "work05.html";
  let options = `width=800,height=600,top=${top},left=${left}, resizable=no,scrollbars=yes`;
  window.open(url,"_blank",options);

});