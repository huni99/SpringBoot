import { use, useEffect, useState } from "react";


function StudyEffect(){
     const [count,setCount]=useState(0);

     function increase(){
        setCount(count+1);
     }
     //렌더링 할 때 마다 실행(Mount, Status 변경)
    useEffect(()=>{
        console.log("Effect1")
    })
    //Mount(첫렌더링)할때만 실행됨
    //의존성 배열
    useEffect(()=>{
        console.log("Effect2")
    },[])

    // Mount할 때 , count(특정한) status가 변경
    useEffect(()=>{
        console.log("Effect2")
    },[count])

    useEffect(()=>{
        //clean up 코드
        return()=>{

        }
    })
    return(
        <>

            <h1>Use Effect</h1>
            <h1>{count}</h1>
            <button onClick={increase}>NEXT</button>
        </>

    )

}
export default StudyEffect;