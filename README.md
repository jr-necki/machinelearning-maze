
# machinelearning-maze
![Language](https://img.shields.io/badge/Language-java-orange.svg) 
![Language](https://img.shields.io/badge/GUI-swing-yellow.svg)
![Language](https://img.shields.io/badge/IDE-eclipse-blue.svg)
![Language](https://img.shields.io/badge/MachineLearning-reinforcement-white.svg)
# 🚀소개
명지대학교 2020 2학기 **머신러닝** 기말과제 프로젝트입니다.<br></br>
머신러닝의 한 영역인 강화학습을 이용하여 미로찾기를 구현하였습니다.
제가 좋아하는 만화인 진격의 거인을 미로찾기에 접목시켜보았습니다.

# ⚙알고리즘
### <경우 나누기>
#### (1) 변경된 좌표 (r,c)에 아무것도 없는 경우<br></br>
해당 좌표를 리턴해준다. MazePanel에서 좌표를 색칠해준다. 여기서 pointList에 색칠된좌표를 저장한다.<br></br>
#### (2)변경된 좌표 (r,c)가 장애물의 좌표인 경우<br></br>
다시 현재 좌표를 리턴해준다. 이미 색칠되어 있으므로 MazePanel에서는 아무것도 안한다.(reward는 -5 깎인다.)<br></br>
#### (3) 변경된 좌표가 장애물에 둘러 쌓인 경우<br></br>
Reward를 통해 장애물에 둘러쌓인 것을 인지 할 수 있다.<br></br>
전 좌표를 리턴해준다. MazePanel에서는 현재 좌표를 회색으로 칠하고 현재 좌표를 전좌표로 변경한다. <br></br>
그렇게 되면 함정에 빠지는 좌표에서 빠져나오고 다음 탐색 때 아예 그좌표에 들어 갈 일이 없게 된다.<br></br>
#### (4) 빠져나왔지만 여전히 장애물로 인해 이동이 불가능한 경우<br></br>
help라는 변수를 통해 이동 불가능한 경우를 알 수 있다.<br></br>
전 전 좌표를 리턴해준다. MazePanel에서는 현재 좌표,전 좌표를 회색으로 칠하고 다시현재좌표는 전 전 좌표로 변경해 준다.<br></br>
*전 좌표, 전전 좌표 등은 pointList에 저장되어 있다.<br></br>

### <최적코스 찾기><br></br>
찾아진 경로의 수가 미로의 사이즈 *2 이상이고 중복된 경로가 나올 경우 경로 탐색을중지한다.<br></br>
그리고 가장 적게 움직인 route를 findShortestPath 메서드를 이용하여 찾는다.그리고 찾은 경로를 미로에 파란색으로 그려준다<br></br>

# ✨화면
<img width=400 src="https://user-images.githubusercontent.com/70804578/118133056-5100a900-b43b-11eb-9061-f7bf575100c7.png"><br></br>
<img width=400 src="https://user-images.githubusercontent.com/70804578/118133201-80afb100-b43b-11eb-9d3d-99353f6fb708.png"><br></br>
진격의 거인 세계관을 설명해주는 창입니다.<br></br>
<img width=400 src="https://user-images.githubusercontent.com/70804578/118133364-b5236d00-b43b-11eb-84d6-303684bb755c.png"><br></br>
거인이 발견된 곳은 갈 수 없는 곳으로 검정색으로 표시합니다. 거인발견이라는 버튼을 누르면 검정색 칸이 추가됩니다.<br></br>
<img width=400 src="https://user-images.githubusercontent.com/70804578/118133959-5ad6dc00-b43c-11eb-8934-7088f793ad29.png"><br></br>
제한 횟수가 있습니다.<br></br>
<img width=400 src="https://user-images.githubusercontent.com/70804578/118133728-206d3f00-b43c-11eb-94bc-17690e6530ed.png"><br></br>
경로 찾기 버튼을 클릭하면 경로가 초록색으로 칠해집니다.<br></br>
<img width=400 src="https://user-images.githubusercontent.com/70804578/118133855-41359480-b43c-11eb-8b98-7aae9857ec33.png"><br></br>
그 중 최적의 경로를 파란색으로 칠해집니다.



# 😐 아쉬운 점
원래의 목적은 할인율의 개념까지 포함하는 것이었습니다..<br></br>
할인율이란 지금 이 경로가 당장보다 먼 미래에 더 좋다 라는 피드백 을 주기 위해 필요한 것입니다.<br></br>
즉, 식으로 표현해보면 보상은 현재 행동을 통해 받는 보상+ 다음 상태에서
받을 보상*할인율+ 그 다음 상태에서 받을 보상*할인율^2 … 즉, 당장 받을 보상+미래보
상의 합으로 나타낼 수 있습니다. 그러나 할인율을 도입하기에 너무 어려워서 생략하고 보상만 다루었습니다..

