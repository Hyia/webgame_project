# webgame_project
web-game with android and web both

# ��ǥ
������ + �ȵ忬�� (feat. DB�� ����)

# �ʿ��� �׸�(?)
		## UI
			* ��ư
				Ȯ��
				���
				���׷��̵�
				(������)�ذ�
				���͸α�
			* ������
				����(�ڿ�)
				��_������_�̵�
				���»���_�����̵���(Į)
				���»���_������(���)
				�Ǽ���
				�ϱ�,�߱�,��� ����
			* ��UI
				���� ������ �� ������ ���� (��ŷ..?)
			* ��ŸUI
				?
		## �ǹ���
			* ����
				����(�ǹ��� �������� �ʼ�)
				�Ǽ���
				�ڿ�����
				���ֻ���
				����
				���ǹ�(Ÿ��)
				����_����, ����_���� (������ ��ä�������ּ���! �� ���� ������� �θ��̴ϴ�.)
			* ��
				����
				��(���� ����) (��� ������ּ��� ������ �����ſ���)
				�ڿ���        (��� ������ּ��� ������ �����ſ���)
				�����
		## ���ַ�
			�ϱ� �߱� ���
		## ������(��Ʈ��)
			���� ����
# �ʿ��� ��� ���(?)
		���� Ư��?	-	����
		������� �̹��� ���ε�	-	����
		�ɷ�ġ��: �� �� ü
				* ���� ��ֺδ� ���ַ� ���ݷ�����
				* �ο� �� �̵��ӵ� ����
				* ü�� ��ֺδ� ���ַ� ü������

		�� �̵��� ���Ϻδ� �̵��ӵ� ���ġ * (������ ����)
		����ʹ� ��������+�̵��ð� ���?
		���� (�α��� ������)
		DB
			* ȸ������(���̵� ��й�ȣ �г��� ����)
			* ����(ȸ������.���̵�, �����̸�, ��������(������, Integer)
			* ��Ÿ ��������(�� ������ ������, ��, ȸ��id(friegn key) �������� �ڿ� ������_�ܺ��ڿ��� ������ ������_���Ϻ��� ) <- ���߿� DB�ɰ� ��
# ������
	������: �������� ���ñ�. ���� ������. ���� ������ �ʼ�.
	����(�ڿ�): ���� �Ҷ� �ʿ��� �ڿ�. �������� ���. �����̴�.
	������: ���� �簢������ ������ �˷��ٶ� �������� ���� ����ϴ� �׸�
# ����(references)
	* ��web
		- kirarafantasia.com
		- https://www.slideshare.net/_ce/db-42498020



# DB

Members Table
�̸�	UserID	UserPW	UserName	UserEmail	UserLevel	SaveProduction
Ÿ��	varchar	varchar	varchar		varchar		int		int
���	PŰ				Nullable			�����ڿ�

Unit
�̸� unitId	uName	atk	hp	spd
Ÿ�� int	varchar int	int	int
��� PŰ



SlotTable
�̸�	slotId	SlotUID amount
Ÿ��	int	int	int
���	PŰ	����FŰ ���ּ�
	auto	Nullable 

Castle Table
�̸� 	UserId	CastleName	CastleKind	LocationId
Ÿ��	varchar	varchar		int		int
���						PŰ

Hero	Table
�̸�	ID	STR	AGI	CON	Owner	LocationID	specialty	status			potrait	sex
Ÿ��	int	int	int	int	varchar	int		int		int			int	bit
���	PŰ				FŰ	FŰ		����Ư��	����			���	����
	auto				UserID			�̱���		(���ݰ�����, ��ȯ��, ���)

Specialty Table(�̱���)
�̸�	ID	kind	?	?
Ÿ��	int	int	?	?
���	PŰ	����	���𰡴ɷ�ġ����ȿ�������ѹ���.
	auto

CastleTroop Table
�̸�	LocationId	slot1	slot2	slot3
Ÿ��	int		int	int	int
���	FŰ		�������̺��������ΰ���

HeroTroop Table
�̸�	HeroID	slot1	slot2	slot3	slot4	slot5	slot6
Ÿ��	int	int	int	int	int	int	int
���	FŰ	�������̺��������ΰ����¿������ǽ����ΰ��̴�


Building Table
�̸�	LocationId	kind	level	roomNumber
Ÿ��	int		int	int	int
���	FŰ		�ǹ����� �ǹ����� ������_���ȣ

Build Table
�̸�	 Name	buildTime	value
Ÿ��	varchar	time		int
���	PŰ	�Ǽ��ð�	�Ҹ��ڿ�
	�ǹ��̸�

Structure Table
�̸�	kind	name	buildtime	value
Ÿ��	int	varchar	time		int
���	PŰ	�ǹ��̸� �Ǽ��ð�	�Ҹ��ڿ�

UnitBuild Table
�̸�	UnitId	name	buildTime	value
Ÿ��	int	varchar	time		int
���	FŰ	�����̸� �缺�ð�	�Ҹ��ڿ�














