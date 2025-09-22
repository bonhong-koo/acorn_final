
const form = document.querySelector("#myForm");
console.log(form);

const saveButton = document.querySelector("#saveBtn");
console.log(saveButton);
 form.addEventListener('submit',function(event){
//    event.preventDefault();
    console.log("submit");
  
});

saveButton.addEventListener('click',function(event){
  event.preventDefault();//기본 동작을 취소
  console.log("click");
  form.submit();
});