


const inputText = document.querySelector("#inputFiled");
console.log(inputText);

inputText.addEventListener('keydown',function(e){
  console.log('keydown:',e.key);
  console.log('keydown:',e.keyCode);

  if(e.keyCode===13){
    alert('전송');
  }
});