package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 설정 관리자는 앱 전체에서 하나만 존재해야 하므로 final로 선언
public final class ConfigManager {
	
	// ===== 1) 싱글톤의 핵심 =====
	// private 생성자 → 외부에서 new ConfigManager() 불가능
	// 객체 생성 경로를 강제로 통제
	private ConfigManager() {}
	
	public static ConfigManager getInstance() {
		return Holder.INSTANCE;
	}
	
	// ConfigManager 안에 오직 인스턴스 생성만 담당하는 내부 클래스를 둠
	// 단순 static 필드 방식은 클래스 로딩 시 인스턴스가 즉시 생성된다. (getInstance()를 호출하지 않아도 생성됨)
	//  Holder 방식: getInstance()가 처음 호출될 때 내부 static 클래스(Holder)가 로딩되며 인스턴스가 생성된다.
	// 이 방식의 장점
		// Lazy Initialization: 실제로 필요할 때만 객체 생성
	 	// Thread-safe: JVM의 클래스 로딩 특성으로 동기화 없이 안전
	private static final class Holder {
		private static final ConfigManager INSTANCE = new ConfigManager();
	}
	
	// ===== 2) Config state =====
	// 설정 상태 정의
	public enum Theme { LIGHT, DARK }

	// 두 값이 앱 전역 설정
	// 어디서 접근하든 같은 값
    private Theme theme = Theme.LIGHT;
    private int fontSize = 16;

    // 읽기 전용 접근
    // 직접 필드 노출 안 함 → 캡슐화
    public Theme getTheme() {
        return theme;
    }

    public int getFontSize() {
        return fontSize;
    }
    
    // ===== 3) Change listeners (Observer) =====
    // 설정 변경 알림을 위한 Listener 인터페이스
    // 설정 바뀌었을 때 미리보기 패널이 알아서 다시 그리도록
    public interface ConfigListener {
        void onConfigChanged();
    }

    // 리스너 목록 관리
    // 설정 변경을 구독(subscribe) 하는 객체들 목록
    private final List<ConfigListener> listeners = new ArrayList<>();

    public void addListener(ConfigListener listener) {
        Objects.requireNonNull(listener);
        listeners.add(listener);
    }

    public void removeListener(ConfigListener listener) {
        listeners.remove(listener);
    }

    // 변경 알림 메서드
    // 알림 도중 리스너가 추가/삭제되면 Exception 발생 가능하므로 복사본을 순회함.
    private void notifyListeners() {
        for (ConfigListener l : new ArrayList<>(listeners)) {
            l.onConfigChanged();
        }
    }
    
    // ===== 4) Mutators (객체의 내부 상태를 "변경"하는 메서드) =====
    // 테마 변경
    public void setTheme(Theme newTheme) {
        Objects.requireNonNull(newTheme);  // null 방지
        if (this.theme == newTheme) return;  // 같은 값이면 아무 것도 안 함 (불필요한 repaint 방지)

        this.theme = newTheme;  // 값 변경
        notifyListeners();  // 리스너 전부에게 알림
    }

    // 폰트 크기 변경
    public void setFontSize(int newFontSize) {
    	// 폰트 크기는 최소 10 최대 36
        int clamped = Math.max(10, Math.min(newFontSize, 36));
        if (this.fontSize == clamped) return;

        this.fontSize = clamped;
        notifyListeners();
    }
	
}
