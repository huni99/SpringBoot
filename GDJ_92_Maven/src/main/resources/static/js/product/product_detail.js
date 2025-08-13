const btn_cls = document.getElementsByClassName("action");
const frm = document.getElementById("frm");
for(el of btn_cls){
	el.addEventListener("click",function(e){
	/*	const kind = this.getAttribute("data-kind");
			console.log(this.dataset.kind);
			console.log(kind);*/
			
		let k = e.target;
		let kind = k.getAttribute("data-kind");
		if(kind=='d'){
			frm.setAttribute("method","post");
			frm.setAttribute("action","/products/delete");
			frm.submit();
		}
		else if(kind=='u'){
			frm.setAttribute("action","/products/update");
			frm.submit();
		}
		
	})
}

const btn_cart = document.getElementById("cartAdd");

btn_cart.addEventListener("click",()=>{
	const productNum= btn_cart.getAttribute("data-product-num");
	const params = new URLSearchParams();
	params.append("productNum",productNum);
	
	fetch("/member/cartAdd",
		{
		   method:"post",	
		   body:params			
		}).then(r=>r.json())
		  .then(r=>{
			if(r>0){
				if(confirm("장바구니 페이지로 이동하시겠습니까?"))
								{
						location.href="/member/cartList";
				}
			}
			
		  }).catch(e=>{
			alert("예외발생");
		  })
	
})



