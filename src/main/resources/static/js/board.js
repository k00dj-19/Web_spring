let index = {
  init: function(){
    $("#btn-save").on("click", ()=>{
      this.save();  // function(){} 을 사용하지 않고, ()=>{} 를 사용한 이유 : this를 바인딩하기 위해서!!
    });  // (실행조건, 실행할 내용)
  },
  
  save: function(){
    //alert("user의 save함수 호출됨");
    let data = {
      title: $("#title").val(),
      content: $("#content").val(),
    };
    
    $.ajax({
      type: "POST",
      url: "/api/board",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function(resp){
      alert("글쓰기가 완료되었습니다.");
      location.href = "/";
    }).fail(function(error){
      alert(JSON.stringify(error));
    }); 
  }
}

index.init();