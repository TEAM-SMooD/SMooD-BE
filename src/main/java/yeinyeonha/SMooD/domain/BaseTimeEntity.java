package yeinyeonha.SMooD.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
@ToString
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false)
    private String createdDay;

    @CreatedDate
    @Column(updatable = false)
    private String createTime;

    @LastModifiedDate
    private String modifiedDay;

    @LastModifiedDate
    private String modifiedTime;

    @PrePersist
    public void onPrePersist() {
        this.createdDay = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.modifiedDay = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        this.modifiedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
