<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <title>SIST Recipe&Food</title>
    
    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!--

TemplateMo 546 Sixteen Clothing

https://templatemo.com/tm-546-sixteen-clothing

-->

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="../assets/css/fontawesome.css">
    <link rel="stylesheet" href="../assets/css/templatemo-sixteen.css">
    <link rel="stylesheet" href="../assets/css/owl.css">
    <link rel="stylesheet" href="../assets/css/food.css">
    <link rel="stylesheet" href="../assets/css/chat.css">
    <style type="text/css">
    .modal-content{
      margin-top: 150px;
    }
    </style>
  </head>

  <body>

    <!-- ***** Preloader Start ***** -->
    <div id="preloader">
        <div class="jumper">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>  
    <!-- ***** Preloader End ***** -->
    
    <!-- Header -->
    <tiles:insertAttribute name="header"/>
    <!-- end Header -->

    <!-- Page Content -->
    <!-- content  -->
    <tiles:insertAttribute name="content"/>
    <!-- content end -->
    
    <!-- footer -->
    <tiles:insertAttribute name="footer"/>
    <!-- footer end -->

    
    <!-- Bootstrap core JavaScript -->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


    <!-- Additional Scripts -->
    <script src="../assets/js/custom.js"></script>
    <script src="../assets/js/owl.js"></script>
    <script src="../assets/js/slick.js"></script>
    <script src="../assets/js/isotope.js"></script>
    <script src="../assets/js/accordions.js"></script>
    

    <script language = "text/javascript"> 
      cleared[0] = cleared[1] = cleared[2] = 0; //set a cleared flag for each field
      function clearField(t){                   //declaring the array outside of the
      if(! cleared[t.id]){                      // function makes it static and global
          cleared[t.id] = 1;  // you could use true and false, but that's more typing
          t.value='';         // with more chance of typos
          t.style.color='#fff';
          }
      }
    </script>
    <!-- <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script> -->
    <script type="text/javascript">
    $(function(){
    	$('#logBtn').click(function(){
    		let id=$('#id').val();
    		if(id.trim()=="")
    		{
    			$('#id').focus();
    			return;
    		}
    		let pwd=$('#pwd').val();
    		if(pwd.trim()=="")
    		{
    			$('#pwd').focus();
    			return;
    		}
    		
    		$.ajax({
    			type:'POST',
    			url:'../main/login.do',
    			data:{"id":id,"pwd":pwd},
    			success:function(res)
    			{
    				let no=res.trim();
    				alert(no);
    				if(no==1)
    				{
    					$('#result').text("ID가 존재하지 않습니다");
    					$('#id').val("");
    					$('#pwd').val("");
    					$('#id').focus();
    				}
    				else if(no==2)
    				{
    					$('#result').text("비밀번호가 틀립니다");
    					$('#pwd').val("");
    					$('#pwd').focus();
    				}
    				else
    				{
    					$('#myModal').modal("hide");
    					$('#id').val("");
    					$('#pwd').val("");
    					$('#result').text("");
    				}
    					
    			}
    		});
    	});
    });
    </script>
    
    <div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4>Login</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <table class="table">
         <tr>
           <td width=25% class="text-right">ID</td>
           <td width=75%>
             <input type=text id="id" class="input-sm" size=15>
           </td>
         </tr>
         <tr>
           <td width=25% class="text-right">PW</td>
           <td width=75%>
             <input type=password id="pwd" class="input-sm" size=15>
           </td>
         </tr> 
         <tr>
           <td class="text-center" colspan="2">
             <span style="color:red" id="result"></span>
           </td>
         </tr>
        </table>
      </div>
      <div class="modal-footer">
        <input type=button class="btn btn-success" value="로그인" id="logBtn">
        <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
  <c:if test="${sessionScope.id!=null }">
  <div id="root"></div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>
  <script src="https://unpkg.com/socket.io-client@2.3.0/dist/socket.io.js"></script>
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
   <script type="text/babel">
     const socket=io.connect("http://localhost:3001")
     class App extends React.Component{
        constructor(props)
        {
           super(props);
           this.state={
              logs:[],
              info:'홍길동'
           }
        }
        componentDidMount() {
            socket.on('chat-msg',(obj)=>{
                const log2=this.state.logs;
                console.log("log2:"+log2)
                log2.push(obj)
                this.setState({logs:log2})
            })
            /*let _this=this;
            axios.get('http://localhost:8080/web/chat/chat.do')
              .then(function(response){
                   console.log(response.data);
                  _this.setState({info:response.data})
              })*/
            $('div#chat').toggleClass('active');
            var $win = $(window);
            var top = $(window).scrollTop(); // 현재 스크롤바의위치값을 반환합니다.
             /*사용자 설정 값 시작*/
            var speed          = 1000;     // 따라다닐 속도 : "slow", "normal", or "fast" or numeric(단위:msec)
            var easing         = 'linear'; // 따라다니는 방법 기본 두가지 linear, swing
            var $layer         = $('div#chat_container'); // 레이어셀렉팅
            var layerTopOffset = 0;   // 레이어 높이 상한선, 단위:px
            $layer.css('position', 'absolute');
            /*사용자 설정 값 끝*/

            // 스크롤 바를 내린 상태에서 리프레시 했을 경우를 위해
            if (top > 0 )
               $win.scrollTop(layerTopOffset+top);
            else
               $win.scrollTop(0);

            //스크롤이벤트가 발생하면
            $(window).scroll(function(){

               var yPosition = $win.scrollTop()+300;
               if (yPosition< 0)
               {
                 yPosition = $win.scrollTop()+300;
               }
               $layer.animate({"top":yPosition }, {duration:speed, easing:easing, queue:false});
             });

          }
          render() {
             return (
              <div>
               <div className={"row"}>
               
               </div>
               <ChatMain logs={this.state.logs}/>
             </div>
             )
          }
    }
    class ChatMain extends React.Component{
       render() {
        const html=this.props.logs.map((m)=>
            <div className={"message-right"}>
                <div className={"message-text"}>{m.message}</div>
            </div>
        )
       return (
       <div id={"chat_container"}>
          <div id={"chat"} className={"active"}>
              <header><h1>Chat</h1></header>
              <section className={"content"}>
                  <div className={"message_content"}>
                      {html}
                  </div>
              </section>
              <ChatForm />
          </div>
       </div>
      )
     }
    }
    class ChatForm extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            message:''
        }
    }
    // 문자를 입력시마다 입력값을 저장
    messageChange(e)
    {
        this.setState({message:e.target.value})
    }
    // 저장된 문자를 서버로 전송
    send(e)
    {
        if(e.key=='Enter')
        {
            e.preventDefault();// 이벤트 종료
            socket.emit('chat-msg',{
                message:'[홍길동]'+this.state.message
            })
            this.setState({message:''})
            //this.state.message='';
        }
    }
    render(){
        return (
            <form>
                <input type={"text"} id={"input_chat"} value={this.state.message}
                  onChange={this.messageChange.bind(this)}
                       onKeyPress={this.send.bind(this)}
                />
            </form>
        )
    }
  }
  ReactDOM.render(<App/>,document.getElementById('root'))
   
   </script>
   </c:if>
  </body>

</html>
