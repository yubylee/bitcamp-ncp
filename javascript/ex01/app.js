const { exec } = require('child_process');
const express = require('express');
const { resolve } = require('path');
const { setTimeout } = require('timers/promises');
const { CLIENT_RENEG_LIMIT } = require('tls');
const app = express()
const port = 3000

/*
const delay = async () => {
    console.log("=====> 잠들다");
    await sleep(5000);
    console.log("=====> 깨어나다");
}
const sleep = (ms) => {
   return new Promise(resolve=>{
       setTimeout(resolve,ms)
   })
}*/

// app.get('/hello', async(req, res) => {

//   await new Promise(resolve => 
//     setTimeout(resolve, 5000)
//   );

//   console.log("Hello!")//콘솔창에 출력할때
//   res.send('Hello!');//웹브라우저에 보낼때 씀
// });

app.get('/exam05_1', async (req, res) => {
  await new Promise(resolve => setTimeout(resolve, 10000));
  
  res.send('console.log("exam05_1 ok!");')
  // for(var i = 0; i < 300000000; i++) {
    //   var r = Math.random() * Math.random() 
    //   * Math.random() * Math.random() * Math.random();
  }
  //res.header("Access-Control-Allow-Origin", "*");
  // }
  )
  
  
  app.get('/exam05_2', (req, res) => {
    //res.header("Access-Control-Allow-Origin", "*");
    res.send('console.log("exam05_2 ok!")');
  })
  
  app.get('/exam05_x', (req, res) => {
    //res.header("Access-Control-Allow-Origin", "*");
    res.send('var rate = 30000');
  })
  
  
  app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
  })
  
  // function handleHello(req, res) {
  //   res.send('Hello!');
  // }