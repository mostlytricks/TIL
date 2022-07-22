-
https://blog.codefactory.ai/electron/create-desktop-app-with-react-and-electron/1-project-setting/
참고할 것


1) electron 실행시 바라볼 "main" 이 정의 되어 있어야 한다. 
- package.json -> 최상위 -> "main":"main.js" 또는 src/build/index.js 등 원하는 대상을 입력할 것

2) electron, electron-packager를 설치해라
- 부분적으로 @electron/get@1.90.0등 단위 모듈별로 설치 가능하니, 설치 로그 시 특이사항 뜨면 분할하여 설치할 것
- ex) npm install --save @electron/get@1.90.0 >> npm install --save @electron

3) 통상적으로는 electron . 으로 electron-start가 가능하지만, react app을 패키징하는 경우 해당 주소를 잡아줘야 한다. 
```json
"scripts":{
  "electron-start" : "electron ."
  "electron-react-start" : "set ELECTRON_START_URL = http://localhost:3001 && electron ."
  "electron-build" : "가이드 문서 참고해서 option볼 것, electron-packager 사용"
  "electron-react-pack" : "yarn build && electron-builder build -c.extraMetadata.main=build/Main.js" 
}
```
- electron_start_url은 main.js에서 실행 시 받는 파라미터.
- main.js에서는 크기 및 electron을 시작하는 url등을 관리한다.
- 



```javascript
const{app, BrowserWindow} = require('electron');
const path = require('path');
const url = require('url');

function createWindow(){
  const win = new BrowserWindow({
    width : 1920,
    height : 1080
  });
  const startUrl = process.env.ELECTRON_START_URL || url.format({
    pathname : path.join(__dirname, '/public/index.html'), // react app의 시작 index.html위치를 잡아줘야 한다.
    protocol:'file:' // 또는 'file' 등
    slashes : true
  })
  win.loadURL(startUrl);
}
app.on('ready', createWindow);
```
