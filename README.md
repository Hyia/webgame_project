# webgame_project
web-game with android and web both

# 목표
웹게임 + 안드연동 (feat. DB와 서버)

# 필요한 그림(?)
		## UI
			* 버튼
				확인
				취소
				업그레이드
				(지휘자)해고
				동맹맺기
			* 아이콘
				수정(자원)
				내_성으로_이동
				병력상태_전장이동중(칼)
				병력상태_복귀중(깃발)
				건설중
				하급,중급,상급 유닛
			* 탭UI
				도시 지휘자 맵 우편함 동맹 (랭킹..?)
			* 기타UI
				?
		## 건물류
			* 마을
				공터(건물을 짓기위한 초석)
				건설중
				자원생산
				유닛생산
				주점
				방어건물(타워)
				성벽_가로, 성벽_세로 (성문과 성채포함해주세요! 두 종류 사방으로 두를겁니다.)
			* 맵
				공터
				성(유저 본성) (깃발 만들어주세요 유저색 넣을거에요)
				자원지        (깃발 만들어주세요 유저색 넣을거에요)
				사냥터
		## 유닛류
			하급 중급 상급
		## 지휘자(포트릿)
			남자 여자
# 필요한 기술 기능(?)
		영웅 특기?	-	보류
		사용자의 이미지 업로드	-	보류
		능력치류: 힘 민 체
				* 힘에 통솔부대 유닛류 공격력증가
				* 민에 맵 이동속도 증가
				* 체에 통솔부대 유닛류 체력증가

		맵 이동은 예하부대 이동속도 평균치 * (지휘자 보정)
		사냥터는 유저레벨+이동시간 비례?
		세션 (로그인 유지용)
		DB
			* 회원정보(아이디 비밀번호 닉네임 메일)
			* 마을(회원정보.아이디, 마을이름, 마을종류(종족등, Integer)
			* 기타 게임정보(각 유저의 소유물, 즉, 회원id(friegn key) 마을정보 자원 소유한_외부자원지 지휘자 지휘자_예하병력 ) <- 나중에 DB쪼갤 것
# 용어사전
	지휘자: 영웅같은 뭐시기. 병력 지휘함. 병력 통제시 필수.
	수정(자원): 뭔가 할때 필요한 자원. 수정으로 명명. 수정이다.
	아이콘: 작은 사각형으로 정보를 알려줄때 직관성을 위해 사용하는 그림
# 참조(references)
	* 웹web
		- kirarafantasia.com
		- https://www.slideshare.net/_ce/db-42498020



# DB

Members Table
이름	UserID	UserPW	UserName	UserEmail	UserLevel	SaveProduction
타입	varchar	varchar	varchar		varchar		int		int
비고	P키				Nullable			보유자원

Unit
이름 unitId	uName	atk	hp	spd
타입 int	varchar int	int	int
비고 P키



SlotTable
이름	slotId	SlotUID amount
타입	int	int	int
비고	P키	유닛F키 유닛수
	auto	Nullable 

Castle Table
이름 	UserId	CastleName	CastleKind	LocationId
타입	varchar	varchar		int		int
비고						P키

Hero	Table
이름	ID	STR	AGI	CON	Owner	LocationID	specialty	status			potrait	sex
타입	int	int	int	int	varchar	int		int		int			int	bit
비고	P키				F키	F키		영웅특기	상태			면상	성별
	auto				UserID			미구현		(공격가는중, 귀환중, 대기)

Specialty Table(미구현)
이름	ID	kind	?	?
타입	int	int	?	?
비고	P키	병종	무언가능력치증가효과에대한무언가.
	auto

CastleTroop Table
이름	LocationId	slot1	slot2	slot3
타입	int		int	int	int
비고	F키		슬롯테이블을값으로가짐

HeroTroop Table
이름	HeroID	slot1	slot2	slot3	slot4	slot5	slot6
타입	int	int	int	int	int	int	int
비고	F키	슬롯테이블을값으로가지는여섯개의슬롯인것이다


Building Table
이름	LocationId	kind	level	roomNumber
타입	int		int	int	int
비고	F키		건물종류 건물레벨 마을내_방번호

Build Table
이름	 Name	buildTime	value
타입	varchar	time		int
비고	P키	건설시간	소모자원
	건물이름

Structure Table
이름	kind	name	buildtime	value
타입	int	varchar	time		int
비고	P키	건물이름 건설시간	소모자원

UnitBuild Table
이름	UnitId	name	buildTime	value
타입	int	varchar	time		int
비고	F키	유닛이름 양성시간	소모자원














