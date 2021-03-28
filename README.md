# ITunesSong
iTunes에 있는 Greenday 음악리스트 애플리케이션

<br>

[![Build Status](https://travis-ci.com/sysout-achieve/ITunesSong.svg?branch=master)](https://travis-ci.com/sysout-achieve/ITunesSong)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
<br>

### 구성 : <br>
- Language : Kotlin <br>
- DI : Hilt <br>
- AAC(MVVM, DataBinding, LiveData, Room, Navigation, Paging) <br>
- Opensource: RxKotlin, Retrofit, Glide, Swiperefreshlayout <br>
- Test : Unit Test(JUnit4)
- TravisCI

<hr>

## 소개
과제에서 주어진 요구사항을 통해 API통신과 내부 DB를 함께 사용해야함을 확인했습니다.<br>
확인한 사항을 효과적으로 처리하기 위해 Repository Pattern에 대한 고민을 많이하여 작성하였습니다.<br>
RepositoryPattern을 이용하여 현재는 내부DB에 저장하고 있지만 서버저장으로 수정 및 확장하게 될 경우 Repository의 변경만으로 의도하는 바를 이룰 수 있습니다.

### Hilt

: di를 적용하여 클래스 간 의존성을 줄여 설계하였습니다.
  


### MVVM

### ViewModel(AAC)

### LiveData

### DataBindnig

### Room

### Paging

paging처리를 SongsFragment와 FavoritesFragment 모두 적용하였습니다.
두 화면의 RecyclerView에서 표현해야할 항목들만 다를 뿐, 같은 기능을 하고 있다 판단하여 하나의 Adapter와 Paging class를 양쪽에서 사용할 수 있도록 추상화하여 구현하였습니다. 

### Glide

## 추가 고려사항
### 1. 확장성을 위한 고려사항
: Domain model과 Dto, Entity를 각각 만들어 사용하였습니다. <br>
각 모델의 내부데이터를 모두 같은 것으로 작성할 수 있지만, 객체의 역할과 책임이 다르기 때문에 새롭게 만들어 사용하였습니다.<br>
이를 통해 외부에서 내려주는 데이터의 모양이 변경되어도 mapper만으로 다른 객체들의 변화를 막을 수 있습니다.<br>
또 Entity를 직접 화면에 출력하기 위한 모델로 사용하지 않아 표현하려는 객체의 모양이 변화하여도 DB테이블은 변경점이 없습니다.

### 2. 안정성을 위한 고려사항
: Repository 중심의 TEST <br>
주어진 과제에서 Favorite Track 저장을 핵심 기능이라 판단하여 Test를 작성했습니다. <br>
Favorite Track 저장을 위한 Repository의 저장 및 호출 기능 위주의 test code를 작성하여 기능을 보장할 수 있었습니다.

### 3. 성능을 위한 고려사항
: DB에서 가져온 전체 Favorite 트랙의 ID를 HashMap으로 관리합니다.<br>
처음 DB에서 호출하여 HashMap에 저장할 때(앱 실행 시 한 번) O(n)의 시간복잡도를 사용합니다.<br>
이후 RecyclerView에서 Favorite(별모양 토글버튼)을 결정할 때는 O(1)의 시간복잡도를 기대할 수 있습니다.<br>
외부에서 객체를 불러오는 시점에 DB에서 체크하는 로직을 사용하려 했으나 페이징 처리가 들어있고 호출하는 갯수의 크기에 따라 시간복잡도가 커지는 상황을 방지하고 싶었습니다. <br>
RecyclerView에서의 binding 시 연산은 사용성에 크게 영향을 줄 수 있는 부분이기 때문에 시간복잡도를 최대한 줄일 수 있는 방안으로 선택했습니다.<br>
이를 통해 같은 화면에서 상대적으로 큰 리소스를 필요로하는 상황이 생겨도 Frame이슈에 대비할 수 있게 됩니다.

### 4. 협업을 위한 고려사항
: 코드에 상수 하드코딩을 지양했습니다.<br>
로직안에서의 상수는 협업 시 상수 변경에 있어 사이드 이펙을 고려하기 어려워진다고 생각했습니다. <br>
목적이 있는 상수를 목적을 알 수 있는 변수명과 함께 선언하여, 사용 목적을 분명하게 만들었습니다.



<hr>

### 시행착오

minSdkTarget이 19 의 애플리케이션을 만들면서 DB접근을 coroutine을 사용했었습니다. (coroutine branch로 작업본 남김) <br>
개발완료 후 테스트 코드 작성 단계에서 21버전 이하의 안드로이드 단말에서 간헐적 crash 이슈가 발생함을 확인하였고, <br> 
Rx로 비동기처리를 변환 적용하였습니다.

이러한 시행착오를 쉽게 넘기지 않아야겠습니다. <br>
기술을 사용할 때 꼼꼼하게 확인하고 적용하는 것에 다시 한 번 경각심을 일깨워주었습니다.
