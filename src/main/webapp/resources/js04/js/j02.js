
  const childButton = document.querySelector("#child");
  console.log(childButton);

  const parentDiv = document.querySelector("#parent");
  console.log(parentDiv);          

  childButton.addEventListener('click',function(event){
    console.log('child click');
    alert('child click');

    //버블링 중지
    event.stopPropagation();
  });

  parentDiv.addEventListener('click',function(event){
    console.log('parent click');
    alert('parent click');
  });

